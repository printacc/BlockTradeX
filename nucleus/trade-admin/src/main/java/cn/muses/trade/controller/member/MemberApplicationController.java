//package cn.muses.trade.controller.member;
//
//import cn.muses.trade.annotation.AccessLog;
//import cn.muses.trade.constant.AdminModule;
//import cn.muses.trade.constant.PageModel;
//import cn.muses.trade.controller.common.BaseAdminController;
//import cn.muses.trade.entity.MemberApplication;
//import cn.muses.trade.model.screen.MemberApplicationScreen;
//import cn.muses.trade.service.LocaleMessageSourceService;
////import cn.muses.trade.service.MemberApplicationService;
//import cn.muses.trade.util.MessageResult;
//import cn.muses.trade.util.PredicateUtils;
////import cn.muses.trade.vendor.provider.SMSProvider;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static cn.muses.trade.entity.QMemberApplication.memberApplication;
//import static org.springframework.util.Assert.notNull;
//
//import static cn.muses.trade.util.MessageResult.success;
//import static cn.muses.trade.util.MessageResult.error;
//
///**
// * @author Hevin  E-mail:bizzanhevin@gmail.com
// * @description 实名审核单
// * @date 2019/12/26 15:05
// */
//@RestController
//@RequestMapping("member/member-application")
//public class MemberApplicationController extends BaseAdminController {
////	@Autowired
////    private SMSProvider smsProvider;
//    @Autowired
//    private MemberApplicationService memberApplicationService;
//    @Autowired
//    private LocaleMessageSourceService messageSource;
//
//    @RequiresPermissions("member:member-application:all")
//    @PostMapping("all")
//    @AccessLog(module = AdminModule.MEMBER, operation = "所有会员MemberApplication认证信息")
//    public MessageResult all() {
//        List<MemberApplication> all = memberApplicationService.findAll();
//        if (all != null && all.size() > 0) {
//            return success(all.toString());
//        }
//        return error(messageSource.getMessage("NO_DATA"));
//    }
//
//    @RequiresPermissions("member:member-application:detail")
//    @PostMapping("detail")
//    @AccessLog(module = AdminModule.MEMBER, operation = "会员MemberApplication认证信息详情")
//    public MessageResult detail(@RequestParam("id") Long id) {
//        MemberApplication memberApplication = memberApplicationService.getOne(id);
//        notNull(memberApplication, "validate id!");
//        return success(memberApplication.toString());
//    }
//
//    @RequiresPermissions("member:member-application:page-query")
//    @PostMapping("page-query")
//    @AccessLog(module = AdminModule.MEMBER, operation = "分页查找会员MemberApplication认证信息")
//    public MessageResult queryPage(PageModel pageModel, MemberApplicationScreen screen) {
//        List<BooleanExpression> booleanExpressions = new ArrayList<>();
//        if (screen.getAuditStatus() != null) {
//            booleanExpressions.add(memberApplication.auditStatus.eq(screen.getAuditStatus()));
//        }
//        if (!StringUtils.isEmpty(screen.getAccount())) {
//            booleanExpressions.add(memberApplication.member.username.like("%" + screen.getAccount() + "%")
//                    //.or(memberApplication.member.mobilePhone.like(screen.getAccount() + "%"))
//                   // .or(memberApplication.member.email.like(screen.getAccount() + "%"))
//                    .or(memberApplication.member.realName.like("%" + screen.getAccount() + "%")));
//        }
//        if(!StringUtils.isEmpty(screen.getCardNo())) {
//            booleanExpressions.add(memberApplication.member.idNumber.like("%" + screen.getCardNo() + "%"));
//        }
//        Predicate predicate = PredicateUtils.getPredicate(booleanExpressions);
//        Page<MemberApplication> all = memberApplicationService.findAll(predicate, pageModel.getPageable());
//        return success(all.toString());
//    }
//
//    @RequiresPermissions("member:member-application:pass")
//    @PatchMapping("{id}/pass")
//    @AccessLog(module = AdminModule.MEMBER, operation = "会员MemberApplication认证通过审核")
//    public MessageResult pass(@PathVariable("id") Long id) {
//        //校验
//        MemberApplication application = memberApplicationService.getOne(id);
//        notNull(application, "validate id!");
//        //业务
//        memberApplicationService.auditPass(application);
//        // 发送通知
//        try {
////			smsProvider.sendCustomMessage(application.getMember().getMobilePhone(), "恭喜！您提交的实名认证申请已通过审核！");
//		} catch (Exception e) {
//			return error(e.getMessage());
//		}
//        //返回
//        return success();
//    }
//
//    @RequiresPermissions("member:member-application:no-pass")
//    @PatchMapping("{id}/no-pass")
//    @AccessLog(module = AdminModule.MEMBER, operation = "会员MemberApplication认证不通过审核")
//    public MessageResult noPass(
//            @PathVariable("id") Long id,
//            @RequestParam(value = "rejectReason", required = false) String rejectReason) {
//        //校验
//        MemberApplication application = memberApplicationService.getOne(id);
//        notNull(application, "validate id!");
//        //业务
//        application.setRejectReason(rejectReason);//拒绝原因
//        memberApplicationService.auditNotPass(application);
//
//        try {
////			smsProvider.sendCustomMessage(application.getMember().getMobilePhone(), "您提交的实名认证申请未通过审核，请查看原因后重新申请！");
//		} catch (Exception e) {
//			return error(e.getMessage());
//		}
//        //返回
//        return success();
//    }
//
//    private void sendMsg() {
//
//    }
//}