package cn.muses.trade.controller.system;

import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.AdminModule;
import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.Department;
import cn.muses.trade.service.DepartmentService;
import cn.muses.trade.util.BindingResultUtil;
import cn.muses.trade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年12月20日
 */
@RestController
@RequestMapping(value = "/system/department")
public class DepartmentController extends BaseAdminController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * 创建或更新部门
     *
     * @param department
     * @return
     */
    @RequiresPermissions("system:department:merge")
    @RequestMapping("merge")
    @AccessLog(module = AdminModule.SYSTEM, operation = "创建或更新部门Department")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult save(@Valid Department department, BindingResult bindingResult) {
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        if (department.getId() != null) {
            department.setCreateTime(departmentService.getOne(department.getId()).getCreateTime());
        }
        departmentService.save(department);
        return success();
    }

    /**
     * 部门详情
     *
     * @param departmentId
     * @return
     */
    @RequiresPermissions("system:department:detail")
    @RequestMapping("detail")
    @AccessLog(module = AdminModule.SYSTEM, operation = "部门Department详情")
    public MessageResult detail(Long departmentId) {
        Department department = departmentService.getDepartmentDetail(departmentId);
        return success(department.toString());
    }

    /**
     * 全部部门
     *
     * @return
     */
    @RequiresPermissions("system:department:all")
    @RequestMapping("all")
    @AccessLog(module = AdminModule.SYSTEM, operation = "所有部门Department")
    public MessageResult allDepartment(PageModel pageModel) {
        Page<Department> all = departmentService.findAll(null, pageModel.getPageable());
        return success(all.toString());
    }

    @RequiresPermissions("system:department:deletes")
    @RequestMapping("deletes")
    @AccessLog(module = AdminModule.SYSTEM, operation = "批量删除部门")
    public MessageResult deletes(@RequestParam(value = "id") Long id) {
        MessageResult result = departmentService.deletes(id);
        return result;
    }

}
