package cn.muses.trade.controller.promotion;

import com.alibaba.fastjson.JSONObject;
import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.*;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.Admin;
import cn.muses.trade.entity.Coin;
import cn.muses.trade.entity.QRewardPromotionSetting;
import cn.muses.trade.entity.RewardPromotionSetting;
import cn.muses.trade.service.CoinService;
import cn.muses.trade.service.LocaleMessageSourceService;
import cn.muses.trade.service.RewardPromotionSettingService;
import cn.muses.trade.util.MessageResult;
import com.querydsl.core.types.dsl.BooleanExpression;
import cn.muses.trade.core.Encrypt;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * 邀请交易佣金奖励设置
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 *
 */
@RestController
@RequestMapping("promotion/reward")
public class RewardPromotionController extends BaseAdminController {

    @Autowired
    private RewardPromotionSettingService rewardPromotionSettingService;

    @Autowired
    private CoinService coinService ;

    @Value("${spark.system.md5.key}")
    private String md5Key;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions("promotion:reward:merge")
    @PostMapping("merge")
    @AccessLog(module = AdminModule.SYSTEM, operation = "创建修改邀请奖励设置")
    public MessageResult merge(@Valid RewardPromotionSetting setting, @SessionAttribute(SysConstant.SESSION_ADMIN)Admin admin
                                ,String unit, @RequestParam(value = "password") String password) {
        password = Encrypt.MD5(password + md5Key);
        org.apache.shiro.util.Assert.isTrue(password.equals(admin.getPassword()),messageSource.getMessage("WRONG_PASSWORD"));
        Coin coin = null ;
        if (setting.getType() != PromotionRewardType.EXCHANGE_TRANSACTION){
            coin = coinService.findByUnit(unit);
            setting.setCoin(coin);
        }
        RewardPromotionSetting rewardPromotionSetting = rewardPromotionSettingService.findByType(setting.getType());
        if(setting.getId()==null&&rewardPromotionSetting!=null) {
            return error("该类型的配置已存在");
        }
        setting.setInfo("{\"one\":"+setting.getOne()+",\"two\":"+setting.getTwo()+"}");
        rewardPromotionSettingService.save(setting);
        return MessageResult.success(messageSource.getMessage("SUCCESS"));
    }

//    @RequiresPermissions("promotion:reward:detail")
//    @PostMapping("detail")
//    @AccessLog(module = AdminModule.SYSTEM, operation = "邀请奖励设置详情")
//    public MessageResult detail(@RequestParam("id")Long id) {
//        RewardPromotionSetting setting = rewardPromotionSettingService.findById(id);
//        if(setting==null){
//            return error("该配置不存在");
//        }
//        String jsonStr = setting.getInfo();
//        if(!StringUtils.isEmpty(jsonStr)){
//            JSONObject json = JSONObject.parseObject(jsonStr);
//            setting.setOne(json.getBigDecimal("one"));
//            setting.setTwo(json.getBigDecimal("two"));
//        }
//
//        return success(messageSource.getMessage("SUCCESS"),setting);
//    }

    /**
     * 查询所有未被禁用的（判断type条件）
     * 默认按照updatetime降序
     *
     * @param enable
     * @param type
     * @return
     */
    @RequiresPermissions("promotion:reward:page-query")
    @GetMapping("page-query")
    @AccessLog(module = AdminModule.SYSTEM, operation = "分页查询邀请奖励设置")
    public MessageResult pageQuery(
            PageModel pageModel,
            @RequestParam(value = "status", defaultValue = "1") BooleanEnum enable,
            @RequestParam(value = "type", required = false) PromotionRewardType type) {
        BooleanExpression predicate = null;
        if (type != null) {
            predicate.andAnyOf(QRewardPromotionSetting.rewardPromotionSetting.type.eq(type));
        }
        Page<RewardPromotionSetting> all = rewardPromotionSettingService.findAll(predicate, pageModel);
        for(RewardPromotionSetting setting : all){
            if(StringUtils.isEmpty(setting.getInfo())) {
                continue ;
            }
            JSONObject jsonObject = JSONObject.parseObject(setting.getInfo());
            setting.setOne(jsonObject.getBigDecimal("one"));
            setting.setTwo(jsonObject.getBigDecimal("two"));
        }
        return success(all.toString());
    }

    @RequiresPermissions("promotion:reward:deletes")
    @DeleteMapping("deletes")
    @AccessLog(module = AdminModule.SYSTEM, operation = "批量删除邀请奖励设置")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult deletes(long[] ids) {
        Assert.notNull(ids, "ids不能为null");
        rewardPromotionSettingService.deletes(ids);
        return MessageResult.success(messageSource.getMessage("SUCCESS"));
    }
}
