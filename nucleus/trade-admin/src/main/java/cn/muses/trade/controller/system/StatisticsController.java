package cn.muses.trade.controller.system;

import cn.muses.trade.annotation.AccessLog;
import cn.muses.trade.constant.AdminModule;
import cn.muses.trade.constant.OrderStatus;
import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.Statistics;
import cn.muses.trade.service.OrderDetailAggregationService;
import cn.muses.trade.service.StatisticsService;
import cn.muses.trade.util.DateUtil;
import cn.muses.trade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/1/8 16:19
 */
@RestController
@RequestMapping("/system/statistics")
public class StatisticsController extends BaseAdminController {
    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private OrderDetailAggregationService orderDetailAggregationService ;

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description 查询新增用户 曲线
     * @date 2019/1/8 16:25
     */
    @RequiresPermissions("system:statistics:member-statistics")
    @PostMapping("member-statistics")
    @AccessLog(module = AdminModule.SYSTEM, operation = "查询新增用户 曲线")
    public MessageResult memberStatistics(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            return error("参数不能为null");
        }
        String sql = "SELECT COUNT(t.id) AS i,t.date FROM( SELECT m.id,DATE_FORMAT( m.registration_time, \"%Y-%m-%e\" ) AS date FROM member m WHERE m.registration_time BETWEEN:startTime AND:endTime ) AS t GROUP BY t.date order BY unix_timestamp(t.date)";
        List statistics = statisticsService.getStatistics(startTime, endTime, sql);
        if (statistics != null && statistics.size() > 0) {
            return success(statistics.toString());
        }
        return error("请求数据不存在");
    }

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description 委托量曲线
     * @date 2019/1/9 13:52
     */
    @RequiresPermissions("system:statistics:delegation-statistics")
    @PostMapping("delegation-statistics")
    @AccessLog(module = AdminModule.SYSTEM, operation = "委托量曲线")
    public MessageResult delegationStatistics(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            return error("参数不能为null");
        }
        String sql = "SELECT t.date, COUNT(t.id) AS i FROM ( SELECT a.id, DATE_FORMAT(a.create_time, \"%Y-%m-%e\") date FROM advertise a WHERE a.`level`= 2 and a.create_time BETWEEN:startTime AND:endTime ) t GROUP BY t.date order BY unix_timestamp(t.date)";
        List<Statistics> list = statisticsService.getStatistics(startTime, endTime, sql);
        if (list != null && list.size() > 0) {
            return success(list.toString());
        }
        return error("请求数据不存在");
    }

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description 交易量 订单量
     * @date 2019/1/9 14:50
     */
    @RequiresPermissions("system:statistics:order-statistics")
    @PostMapping("order-statistics")
    @AccessLog(module = AdminModule.SYSTEM, operation = "交易量 订单量")
    public MessageResult orderStatistics(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            return error("参数不能为null");
        }
        String sql = "SELECT t.date, COUNT(t.id) AS i FROM ( SELECT o.id, DATE_FORMAT(o.pay_time, \"%Y-%m-%e\") date FROM otc_order o WHERE o.`status` = 3 AND o.pay_time BETWEEN:startTime AND:endTime ) t GROUP BY t.date order BY unix_timestamp(t.date)";
        List statistics = statisticsService.getStatistics(startTime, endTime, sql);
        if (statistics != null && statistics.size() > 0) {
            return success(statistics.toString());
        }
        return error("请求数据不存在");
    }

    @RequiresPermissions("system:statistics:dashboard")
    @PostMapping("dashboard")
    @AccessLog(module = AdminModule.SYSTEM, operation = "dashboard")
    public MessageResult dashboard() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        //今日新增用户
        int regMember = statisticsService.getLatelyRegMember(0);
        map.put("regMember", regMember);
        //今日新增订单
        String today = DateUtil.getDate();
        int orderNum = statisticsService.getLatelyOrder(today, today, -1);
        map.put("orderNum", orderNum);
        //订单状态统计
        int unpaidOrderNum = statisticsService.getLatelyOrder(OrderStatus.NONPAYMENT);//未支付
        map.put("unpaidOrderNum", unpaidOrderNum);
        int unconfirmedOrderNum = statisticsService.getLatelyOrder(OrderStatus.PAID);//已付款待确认订单
        map.put("unconfirmedOrderNum", unconfirmedOrderNum);
        int appealOrderNum = statisticsService.getLatelyOrder(OrderStatus.APPEAL);//申诉中订单数
        map.put("appealOrderNum", appealOrderNum);
        int completedOrderNum = statisticsService.getLatelyOrder(OrderStatus.COMPLETED);//已完成订单数
        map.put("completedOrderNum", completedOrderNum);
        //即将到期的广告数量
        int latelyAdvertise = statisticsService.getLatelyAdvertise(1);
        //需要实名审核的数量
        return success(map.toString());
    }

    /*@RequiresPermissions("system:statistics:order-rate")
    @PostMapping("order-rate")
    @AccessLog(module = AdminModule.SYSTEM, operation = "订单手续费统计")
    public Pagenation<OrderDetailAggregation> detail(
                     @RequestParam(required = false)PageParam pageParam,
                     @RequestParam(value = "memberId",defaultValue = "0")Long memberId,
                     @RequestParam(value = "orderType",required = false)OrderTypeEnum orderType,
                     @RequestParam(value = "coinName",required = false)String coinName
                                                     ){
        return orderDetailAggregationService.getDetail(pageParam,memberId,coinName,orderType);
    }*/
}


