package cn.muses.trade.controller.system;

import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.AdminModule;
import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.dto.CoinDTO;
import cn.muses.trade.dto.CoinprotocolDTO;
import cn.muses.trade.entity.Coinext;
import cn.muses.trade.entity.Coinprotocol;
import cn.muses.trade.entity.QCoinext;
import cn.muses.trade.model.screen.CoinextScreen;
import cn.muses.trade.service.CoinService;
import cn.muses.trade.service.CoinextService;
import cn.muses.trade.service.CoinprotocolService;
import cn.muses.trade.util.BindingResultUtil;
import cn.muses.trade.util.MessageResult;
import cn.muses.trade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * 币种扩展管理
 */
@Slf4j
@RestController
@RequestMapping("/system/coinext")
public class CoinextController extends BaseAdminController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private CoinprotocolService coinprotocolService;

    @Autowired
    private CoinextService coinextService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequiresPermissions("system:coinext:coin-list")
    @GetMapping("/coin-list")
    @AccessLog(module = AdminModule.SYSTEM, operation = "币种扩展里获取币种列表")
    public MessageResult coinList() {

        List<CoinDTO> list = coinService.list();

        return success(list.toString());
    }

    @RequiresPermissions("system:coinext:protocol-list")
    @GetMapping("/protocol-list")
    @AccessLog(module = AdminModule.SYSTEM, operation = "币种扩展里获取币种协议列表")
    public MessageResult protocolList() {

        List<CoinprotocolDTO> list = coinprotocolService.list();

        return success(list.toString());
    }

    @RequiresPermissions("system:coinext:page-query")
    @PostMapping("/page-query")
    @AccessLog(module = AdminModule.SYSTEM, operation = "获取币种扩展列表")
    public MessageResult pageQuery(PageModel pageModel, CoinextScreen coinextScreen) {

        List<BooleanExpression> booleanExpressions = new ArrayList<>();

        if (!StringUtils.isBlank(coinextScreen.getCoinname())) {
            booleanExpressions.add(QCoinext.coinext.coinname.eq(coinextScreen.getCoinname()));
        }

        if (!StringUtils.isBlank(coinextScreen.getExt())) {
            booleanExpressions.add(QCoinext.coinext.ext.eq(coinextScreen.getExt()));
        }

        Predicate predicate = PredicateUtils.getPredicate(booleanExpressions);
        Page<Coinext> all = coinextService.findAll(predicate, pageModel.getPageable());
        return success(all.toString());
    }

    @RequiresPermissions("system:coinext:merge")
    @PostMapping("/merge")
    @AccessLog(module = AdminModule.SYSTEM, operation = "创建/修改币种扩展")
    public MessageResult merge(@Valid Coinext coinext, BindingResult bindingResult) {
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }

        // 查询是否存在
        Coinext one = coinextService.findFirstByCoinnameAndProtocol(coinext.getCoinname(), coinext.getProtocol());
        if (coinext.getId() != null) {
            if (one != null && !one.getId().equals(coinext.getId())) {
                result = error("当前协议的币种已存在");
                return result;
            }
        } else if (one != null) {
            result = error("当前协议的币种已存在");
            return result;
        }

        Coinprotocol byProtocol = coinprotocolService.findByProtocol(coinext.getProtocol());

        if (byProtocol == null) {
            result = error("当前协议不存在");
            return result;
        }

        coinext.setProtocolname(byProtocol.getProtocolname());

        // 删除redis缓存
        redisTemplate.delete("coinext");


        coinext = coinextService.save(coinext);
        result = success("操作成功");
        result.setData(coinext);
        return result;
    }

}
