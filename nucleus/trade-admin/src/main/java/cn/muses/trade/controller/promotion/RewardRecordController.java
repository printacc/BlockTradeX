package cn.muses.trade.controller.promotion;

import cn.muses.trade.constant.PageModel;
import cn.muses.trade.model.RewardRecordScreen;
import cn.muses.trade.service.RewardRecordService;
import cn.muses.trade.util.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 奖励记录
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 *
 */
@RestController
@RequestMapping("promotion/reward-record")
public class RewardRecordController {

    @Autowired
    private RewardRecordService rewardRecordService ;

    @PostMapping("page-query")
    public MessageResult page(PageModel pageModel, RewardRecordScreen screen){
        return null;
    }
}
