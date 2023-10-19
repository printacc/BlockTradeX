package cn.muses.trade.event;

import com.alibaba.fastjson.JSONObject;
import cn.muses.trade.constant.PromotionRewardType;
import cn.muses.trade.constant.RewardRecordType;
import cn.muses.trade.dao.MemberDao;
import cn.muses.trade.entity.*;
import cn.muses.trade.service.MemberWalletService;
import cn.muses.trade.service.RewardPromotionSettingService;
import cn.muses.trade.service.RewardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

import static cn.muses.trade.util.BigDecimalUtils.*;
import static cn.muses.trade.util.BigDecimalUtils.add;
import static cn.muses.trade.util.BigDecimalUtils.getRate;
import static cn.muses.trade.util.BigDecimalUtils.mulRound;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月22日
 */
@Service
public class OrderEvent {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private RewardRecordService rewardRecordService;
    @Autowired
    private RewardPromotionSettingService rewardPromotionSettingService;

//    public void onOrderCompleted(Order order) {
//        Member member = memberDao.findOne(order.getMemberId());
//        member.setTransactions(member.getTransactions() + 1);
//        Member member1 = memberDao.findOne(order.getCustomerId());
//        member1.setTransactions(member1.getTransactions() + 1);
//        RewardPromotionSetting rewardPromotionSetting = rewardPromotionSettingService.findByType(PromotionRewardType.TRANSACTION);
//        if (rewardPromotionSetting != null) {
//            Member[] array = {member, member1};
//            Arrays.stream(array).forEach(
//                    x -> {
//                        if (x.getTransactions() == 1 && x.getInviterId() != null) {
//                            Member member2 = memberDao.findOne(x.getInviterId());
//                            MemberWallet memberWallet1 = memberWalletService.findByCoinAndMember(rewardPromotionSetting.getCoin(), member2);
//                            BigDecimal amount1 = mulRound(order.getNumber(), getRate(JSONObject.parseObject(rewardPromotionSetting.getInfo()).getBigDecimal("one")));
//                            memberWallet1.setBalance(add(memberWallet1.getBalance(), amount1));
//                            memberWalletService.save(memberWallet1);
//                            RewardRecord rewardRecord1 = new RewardRecord();
//                            rewardRecord1.setAmount(amount1);
//                            rewardRecord1.setCoin(rewardPromotionSetting.getCoin());
//                            rewardRecord1.setMember(member2);
//                            rewardRecord1.setRemark(rewardPromotionSetting.getType().getCnName());
//                            rewardRecord1.setType(RewardRecordType.PROMOTION);
//                            rewardRecordService.save(rewardRecord1);
//                            if (member2.getInviterId() != null) {
//                                Member member3 = memberDao.findOne(member2.getInviterId());
//                                MemberWallet memberWallet2 = memberWalletService.findByCoinAndMember(rewardPromotionSetting.getCoin(), member3);
//                                BigDecimal amount2 = mulRound(order.getNumber(), getRate(JSONObject.parseObject(rewardPromotionSetting.getInfo()).getBigDecimal("two")));
//                                memberWallet2.setBalance(add(memberWallet2.getBalance(), amount2));
//                                RewardRecord rewardRecord2 = new RewardRecord();
//                                rewardRecord2.setAmount(amount2);
//                                rewardRecord2.setCoin(rewardPromotionSetting.getCoin());
//                                rewardRecord2.setMember(member3);
//                                rewardRecord2.setRemark(rewardPromotionSetting.getType().getCnName());
//                                rewardRecord2.setType(RewardRecordType.PROMOTION);
//                                rewardRecordService.save(rewardRecord2);
//                            }
//                        }
//                    }
//            );
//        }
//    }
}
