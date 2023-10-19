package cn.muses.trade.controller.activity;

import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.MemberSignRecord;
import cn.muses.trade.model.screen.MemberSignRecordScreen;
import cn.muses.trade.model.vo.MemberSignRecordVO;
import cn.muses.trade.service.MemberSignRecordService;
import cn.muses.trade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Description: 会员签到记录
 * @date 2019/5/4
 */
@RestController
@RequestMapping("activity/member-sign-record")
public class MemberSignRecordControler extends BaseAdminController {
    @Autowired
    private MemberSignRecordService service;

    @RequiresPermissions("activity:member-sign-record:page-query")
    @GetMapping("page-query")
    public MessageResult pageQuery(MemberSignRecordScreen screen, PageModel pageModel) {
        Page<MemberSignRecord> source = service.findAllScreen(screen, pageModel);
        Page<MemberSignRecordVO> page = source.map(x -> MemberSignRecordVO.getMemberSignRecordVO(x));
        return success(page.toString());
    }
}
