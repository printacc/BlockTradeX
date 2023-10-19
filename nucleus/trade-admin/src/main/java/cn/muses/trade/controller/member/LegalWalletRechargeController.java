package cn.muses.trade.controller.member;

import cn.muses.trade.constant.LegalWalletState;
import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.LegalWalletRecharge;
import cn.muses.trade.entity.MemberWallet;
import cn.muses.trade.entity.QLegalWalletRecharge;
import cn.muses.trade.model.screen.LegalWalletRechargeScreen;
import cn.muses.trade.service.LegalWalletRechargeService;
import cn.muses.trade.service.MemberWalletService;
import cn.muses.trade.util.MessageResult;
import cn.muses.trade.util.PredicateUtils;
import com.mysema.commons.lang.Assert;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * GS
 */
@RestController
@RequestMapping("legal-wallet-recharge")
public class LegalWalletRechargeController extends BaseAdminController {
    @Autowired
    private LegalWalletRechargeService legalWalletRechargeService;
//    @Autowired
//    private MemberWalletService walletService;

    @GetMapping("page")
    public MessageResult page(
            PageModel pageModel,
            LegalWalletRechargeScreen screen) {
        Predicate predicate = getPredicate(screen);
        Page<LegalWalletRecharge> page = legalWalletRechargeService.findAll(predicate, pageModel);
        return success(page.toString());
    }

//    @GetMapping("{id}")
//    public MessageResult id(@PathVariable("id") Long id) {
//        LegalWalletRecharge legalWalletRecharge = legalWalletRechargeService.findOne(id);
//        Assert.notNull(legalWalletRecharge, "validate id!");
//        return success(legalWalletRecharge.toString());
//    }

    //充值通过
//    @PatchMapping("{id}/pass")
//    public MessageResult pass(@PathVariable("id") Long id) {
//        //充值校验
//        LegalWalletRecharge legalWalletRecharge = legalWalletRechargeService.findOne(id);
//        Assert.notNull(legalWalletRecharge, "validate id!");
//        Assert.isTrue(legalWalletRecharge.getState() == LegalWalletState.APPLYING, "申请已经结束!");
//        //校验钱包
//        MemberWallet wallet = walletService.findByCoinAndMember(legalWalletRecharge.getCoin(), legalWalletRecharge.getMember());
//        org.springframework.util.Assert.notNull(wallet, "wallet null!");
//        //充值请求通过业务
//        legalWalletRechargeService.pass(wallet, legalWalletRecharge);
//        return success();
//    }

    //虚假充值
//    @PatchMapping("{id}/no-pass")
//    public MessageResult noPass(@PathVariable("id") Long id) {
//        LegalWalletRecharge legalWalletRecharge = legalWalletRechargeService.findOne(id);
//        Assert.notNull(legalWalletRecharge, "validate id!");
//        Assert.isTrue(legalWalletRecharge.getState() == LegalWalletState.APPLYING, "申请已经结束!");
//        legalWalletRechargeService.noPass(legalWalletRecharge);
//        return success();
//    }

    //条件
    private Predicate getPredicate(LegalWalletRechargeScreen screen) {
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        if (StringUtils.isNotBlank(screen.getUsername())) {
            booleanExpressions.add(QLegalWalletRecharge.legalWalletRecharge.member.username.eq(screen.getUsername()));
        }
        if (screen.getStatus() != null) {
            booleanExpressions.add(QLegalWalletRecharge.legalWalletRecharge.state.eq(screen.getStatus()));
        }
        if (StringUtils.isNotBlank(screen.getCoinName())) {
            booleanExpressions.add(QLegalWalletRecharge.legalWalletRecharge.coin.name.eq(screen.getCoinName()));
        }
        return PredicateUtils.getPredicate(booleanExpressions);
    }
}
