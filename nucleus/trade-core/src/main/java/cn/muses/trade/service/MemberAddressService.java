package cn.muses.trade.service;

import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.core.Model;
import cn.muses.trade.dao.CoinDao;
import cn.muses.trade.dao.MemberAddressDao;
import cn.muses.trade.entity.Coin;
import cn.muses.trade.entity.MemberAddress;
import cn.muses.trade.pagination.Criteria;
import cn.muses.trade.pagination.Restrictions;
import cn.muses.trade.service.Base.BaseService;
import cn.muses.trade.util.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月26日
 */
@Service
public class MemberAddressService extends BaseService {
    @Autowired
    private MemberAddressDao memberAddressDao;
    @Autowired
    private CoinDao coinDao;

    public MessageResult addMemberAddress(Long memberId, String address, String unit, String remark) {
        Coin coin = coinDao.findByUnit(unit);
        if (coin == null || coin.getCanWithdraw().equals(BooleanEnum.IS_FALSE)) {
            return MessageResult.error(600, "The currency does not support withdrawals");
        }
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddress(address);
        memberAddress.setCoin(coin);
        memberAddress.setMemberId(memberId);
        memberAddress.setRemark(remark);
        MemberAddress memberAddress1=memberAddressDao.saveAndFlush(memberAddress);
        if (memberAddress1!=null){
            return MessageResult.success();
        }else {
            return MessageResult.error( "failed");
        }
    }

    public MessageResult deleteMemberAddress(Long memberId,Long addressId){
        int is=memberAddressDao.deleteMemberAddress(new Date(), addressId, memberId);
        if (is>0){
            return MessageResult.success();
        }else {
            return MessageResult.error("failed");
        }
    }

    public Page<MemberAddress> pageQuery(int pageNo, Integer pageSize, long id,String unit) {
        Sort orders = Criteria.sortStatic("id.desc");
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
        Criteria<MemberAddress> specification = new Criteria<>();
        specification.add(Restrictions.eq("memberId",id,false));
        specification.add(Restrictions.eq("status", CommonStatus.NORMAL, false));
        specification.add(Restrictions.eq("coin.unit",unit,false));
        return memberAddressDao.findAll(specification, pageRequest);
    }

    public List<Map<String,String>> queryAddress(long userId,String coinId)  {
        try {
            return new Model("member_address")
                    .field(" remark,address")
                    .where("member_id=? and coin_id=? and status=?", userId, coinId, CommonStatus.NORMAL.ordinal())
                    .select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<MemberAddress> findByMemberIdAndAddress(long userId,String address){
        return memberAddressDao.findAllByMemberIdAndAddressAndStatus(userId,address,CommonStatus.NORMAL);
    }
}
