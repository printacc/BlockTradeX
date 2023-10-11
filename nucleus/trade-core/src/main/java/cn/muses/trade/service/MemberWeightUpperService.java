package cn.muses.trade.service;

import cn.muses.trade.dao.MemberWeightUpperDao;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.MemberWeightUpper;
import cn.muses.trade.pagination.Criteria;
import cn.muses.trade.pagination.Restrictions;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sulinxin
 */
@Service
public class MemberWeightUpperService extends BaseService {

    @Autowired
    private MemberWeightUpperDao memberWeightUpperDao;
    @Autowired
    private MemberService memberService;

    public MemberWeightUpper findMemberWeightUpperByMemberId(Long memberId) {
        Criteria<MemberWeightUpper> specification = new Criteria<MemberWeightUpper>();
        specification.add(Restrictions.eq("memberId", memberId, false));
        return null;
//        return memberWeightUpperDao.getOne(specification);
    }

    public List<MemberWeightUpper> findAllByUpperIds(String uppers) {
        String[] idss = uppers.split(",");
        List<Long> ids = new ArrayList<>();
        for(String id:idss){
            ids.add(Long.parseLong(id));
        }
        return memberWeightUpperDao.findAllByUpperIds(ids);
    }

    public MemberWeightUpper saveMemberWeightUpper(Member member) {
        MemberWeightUpper memberWeightUpper = this.findMemberWeightUpperByMemberId(member.getId());
        if(memberWeightUpper!=null){
            return memberWeightUpper;
        }
        memberWeightUpper = new MemberWeightUpper();
        //找上级 如果有上级
        if(member.getInviterId()!=null){
            Member inviter = memberService.findOne(member.getInviterId());
            //有上级
            MemberWeightUpper upper = this.saveMemberWeightUpper(inviter);
            memberWeightUpper.setFirstMemberId(upper.getFirstMemberId());
            memberWeightUpper.setRate(0);
            memberWeightUpper.setMemberId(member.getId());
            String uppers = upper.getUpper();
            if(uppers==null || "".equals(uppers.trim())){
                uppers = upper.getMemberId().toString();
            }else {
                uppers = uppers+","+upper.getMemberId();
            }
            memberWeightUpper.setUpper(uppers);
        }else {
            //最上级
            if("1".equals(member.getSuperPartner())){
                memberWeightUpper.setRate(100);
            }else {
                memberWeightUpper.setRate(0);
            }
            memberWeightUpper.setFirstMemberId(member.getId());
            memberWeightUpper.setMemberId(member.getId());
            memberWeightUpper.setUpper(null);

        }
        memberWeightUpper = memberWeightUpperDao.save(memberWeightUpper);
        return memberWeightUpper;
    }

    public MemberWeightUpper saveAgentWeightUpper(Member member) {
        MemberWeightUpper memberWeightUpper = this.findMemberWeightUpperByMemberId(member.getId());
        if(memberWeightUpper!=null){
            return memberWeightUpper;
        }
        memberWeightUpper = new MemberWeightUpper();
        //找上级 如果有上级
        if(member.getInviterId()!=null){
            Member inviter = memberService.findOne(member.getInviterId());
            //有上级
            MemberWeightUpper upper = this.saveAgentWeightUpper(inviter);
            memberWeightUpper.setFirstMemberId(upper.getFirstMemberId());
            memberWeightUpper.setRate(0);
            memberWeightUpper.setMemberId(member.getId());
            String uppers = upper.getUpper();
            if(uppers==null || "".equals(uppers.trim())){
                uppers = upper.getMemberId().toString();
            }else {
                uppers = uppers+","+upper.getMemberId();
            }
            memberWeightUpper.setUpper(uppers);
        }else {
            //最上级
            memberWeightUpper.setRate(0);
            memberWeightUpper.setFirstMemberId(member.getId());
            memberWeightUpper.setMemberId(member.getId());
            memberWeightUpper.setUpper(null);

        }
        memberWeightUpper = memberWeightUpperDao.save(memberWeightUpper);
        return memberWeightUpper;
    }
}
