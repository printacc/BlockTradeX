package cn.muses.trade.controller.system;

import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.AdminModule;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.WebsiteInformation;
import cn.muses.trade.service.WebsiteInformationService;
import cn.muses.trade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description 站点信息
 * @date 2019/1/26 10:41
 */
@RestController
@RequestMapping("/system/website-information")
public class WebsiteInformationController extends BaseAdminController {
    @Autowired
    private WebsiteInformationService websiteInformationService;

    @RequiresPermissions("system:website-information:find-one")
    @GetMapping("/find-one")
    @AccessLog(module = AdminModule.SYSTEM, operation = "站点信息WebsiteInformation")
    public MessageResult get() {
        WebsiteInformation one = websiteInformationService.fetchOne();
        if (one == null) {
            return error("Please add settings!(admin/websiteInformation/modify)");
        }
        return success("get success", one);
    }


    @RequiresPermissions("system:website-information:alter")
    @PutMapping("/alter")
    @AccessLog(module = AdminModule.SYSTEM, operation = "更新站点信息WebsiteInformation")
    public MessageResult modify(WebsiteInformation websiteInformation) {
        WebsiteInformation one = websiteInformationService.fetchOne();
        if (one == null) {
            websiteInformation.setId(1L);
        } else {
            websiteInformation.setId(one.getId());
        }
        WebsiteInformation save = websiteInformationService.save(websiteInformation);
        return success(save.toString());

    }

}
