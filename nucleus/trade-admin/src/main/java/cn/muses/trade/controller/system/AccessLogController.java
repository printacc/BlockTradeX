package cn.muses.trade.controller.system;

import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.AdminModule;
import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.AdminAccessLog;
import cn.muses.trade.entity.QAdmin;
import cn.muses.trade.entity.QAdminAccessLog;
import cn.muses.trade.service.AdminAccessLogService;
import cn.muses.trade.service.AdminService;
import cn.muses.trade.util.MessageResult;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description 日志管理
 * @date 2019/12/22 17:27
 */
@Slf4j
@RestController
@RequestMapping("/system/access-log")
@Transactional(readOnly = true)
public class AccessLogController extends BaseAdminController {

    @Autowired
    private AdminAccessLogService adminAccessLogService;

    @Autowired
    private AdminService adminService ;

    @RequiresPermissions("system:access-log:all")
    @GetMapping("/all")
    @AccessLog(module = AdminModule.SYSTEM, operation = "所有操作/访问日志AdminAccessLog")
    public MessageResult all() {
        List<AdminAccessLog> adminAccessLogList = adminAccessLogService.queryAll();
        return success(adminAccessLogList.toString());
    }

    @RequiresPermissions("system:access-log:detail")
    @GetMapping("/{id}")
    @AccessLog(module = AdminModule.SYSTEM, operation = "操作/访问日志AdminAccessLog 详情")
    public MessageResult detail(@PathVariable("id") Long id) {
        AdminAccessLog adminAccessLog = adminAccessLogService.queryById(id);
        notNull(adminAccessLog, "validate id!");
        return success(adminAccessLog.toString());
    }

    @RequiresPermissions("system:access-log:page-query")
    @GetMapping("/page-query")
    @AccessLog(module = AdminModule.SYSTEM, operation = "分页查找操作/访问日志AdminAccessLog")
    public MessageResult pageQuery(
            PageModel pageModel,
            @RequestParam(value = "adminName", required = false) String adminName,
            @RequestParam(value = "module", required = false) AdminModule module) {

        List<BooleanExpression> list = new ArrayList<>();
        list.add(QAdmin.admin.id.eq(QAdminAccessLog.adminAccessLog.adminId));
        if (!StringUtils.isEmpty(adminName)) {
            list.add(QAdmin.admin.username.like("%"+adminName+"%"));
        }
        if(module!=null) {
            list.add(QAdminAccessLog.adminAccessLog.module.eq(module));
        }
        Page<AdminAccessLog> all = adminAccessLogService.page(list, pageModel);
        return success(all.toString());
    }


}
