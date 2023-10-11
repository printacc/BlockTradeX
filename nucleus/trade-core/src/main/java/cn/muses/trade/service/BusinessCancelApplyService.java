package cn.muses.trade.service;

import cn.muses.trade.constant.CertifiedBusinessStatus;
import cn.muses.trade.dao.*;
import cn.muses.trade.entity.BusinessCancelApply;
import cn.muses.trade.entity.Member;
import cn.muses.trade.service.Base.TopBaseService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusinessCancelApplyService extends TopBaseService<BusinessCancelApply,BusinessCancelApplyDao>{

    @Override
    @Autowired
    public void setDao(BusinessCancelApplyDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private OrderDao orderDao ;

    @Autowired
    private AppealDao appealDao ;

    @Autowired
    private AdvertiseDao advertiseDao ;

    @Autowired
    private MemberDao memberDao ;


    public List<BusinessCancelApply> findByMemberAndStaus(Member member, CertifiedBusinessStatus status){
        return dao.findByMemberAndStatusOrderByIdDesc(member,status);
    }

    public List<BusinessCancelApply> findByMember(Member member){
        return dao.findByMemberOrderByIdDesc(member);
    }

    public Map<String,Object> getBusinessOrderStatistics(Long memberId) {
        Map<String,Object> result = new HashMap<>();
       List<Object[]> list = orderDao.getBusinessStatistics(memberId);
       if(list!=null && list.size()>0){
           result.put("fee",list.get(0)[0]);
           result.put("money",list.get(0)[1]);
       }
       return result;
    }

    public  Map<String,Object> getBusinessAppealStatistics(Long memberId){
        Map<String,Object> map = new HashedMap();
        Long complainantNum = appealDao.getBusinessAppealInitiatorIdStatistics(memberId);
        Long defendantNum = appealDao.getBusinessAppealAssociateIdStatistics(memberId);
        map.put("defendantNum",defendantNum);
        map.put("complainantNum",complainantNum);
        return map ;
    }

    public Long getAdvertiserNum(Long memberId) {
        Member member = memberDao.getOne(memberId);
        return advertiseDao.getAdvertiseNum(member);
    }

    public long countAuditing(){
        return dao.countAllByStatus(CertifiedBusinessStatus.CANCEL_AUTH);
    }


}
