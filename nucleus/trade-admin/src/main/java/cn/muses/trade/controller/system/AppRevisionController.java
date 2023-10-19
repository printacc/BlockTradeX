package cn.muses.trade.controller.system;

import cn.muses.trade.constant.PageModel;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.AppRevision;
import cn.muses.trade.model.create.AppRevisionCreate;
import cn.muses.trade.model.update.AppRevisionUpdate;
import cn.muses.trade.service.AppRevisionService;
import cn.muses.trade.util.BindingResultUtil;
import cn.muses.trade.util.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/2416:31
 */
@RestController
@RequestMapping("system/app-revision")
public class AppRevisionController extends BaseAdminController {
    @Autowired
    private AppRevisionService service;

    //新增
    @PostMapping
    public MessageResult create(@Valid AppRevisionCreate model, BindingResult bindingResult) {
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        service.save(model);
        return success();
    }

    //更新
//    @PutMapping("{id}")
//    public MessageResult put(@PathVariable("id") Long id, AppRevisionUpdate model) {
//        AppRevision appRevision = service.findById(id);
//        Assert.notNull(appRevision, "validate appRevision id!");
//        service.update(model, appRevision);
//        return success();
//    }
//
//    //详情
//    @GetMapping("{id}")
//    public MessageResult get(@PathVariable("id") Long id) {
//        AppRevision appRevision = service.findById(id);
//        Assert.notNull(appRevision, "validate appRevision id!");
//        return success(appRevision);
//    }

    //分页
    @GetMapping("page-query")
    public MessageResult get(PageModel pageModel) {
        return success(service.findAll(null, pageModel).toString());
    }
}
