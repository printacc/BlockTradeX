package cn.muses.trade.service;

import cn.muses.trade.dao.MemberInviteStasticDao;
import cn.muses.trade.dao.MemberInviteStasticRankDao;
import cn.muses.trade.entity.MemberInviteStastic;
import cn.muses.trade.entity.MemberInviteStasticRank;
import cn.muses.trade.pagination.Criteria;
import cn.muses.trade.pagination.Restrictions;
import cn.muses.trade.service.Base.BaseService;
import cn.muses.trade.vo.MemberInviteStasticVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInviteStasticService extends BaseService {

	@Autowired
	private MemberInviteStasticDao memberInviteStasticDao;

	@Autowired
	private MemberInviteStasticRankDao memberInviteStasticRankDao;

	public MemberInviteStastic findByMemberId(Long id) {
		return memberInviteStasticDao.findByMemberId(id);
	}

	public MemberInviteStastic findById(Long id) {
	  return memberInviteStasticDao.getOne(id);
//		return memberInviteStasticDao.findById(id);
	}

	public MemberInviteStasticRank findByRankId(Long id) {
		return memberInviteStasticRankDao.findById(id);
	}

	/**
	 * 获取分佣总额前N
	 * @return
	 */
	public List<MemberInviteStastic> topRewardAmount(int count){
		return memberInviteStasticDao.getTopTotalAmount(count);
	}

	public MemberInviteStastic save(MemberInviteStastic memberInviteStastic) {
		return memberInviteStasticDao.save(memberInviteStastic);
	}

	public MemberInviteStasticRank saveRank(MemberInviteStasticRank memberInviteStasticRank) {
		return memberInviteStasticRankDao.save(memberInviteStasticRank);
	}

	/**
	 * 获取邀请人数前N
	 * @param count
	 * @return
	 */
	public List<MemberInviteStastic> topInviteCount(int count) {
		return memberInviteStasticDao.getTopInviteCount(count);
	}

	/**
	 * 根据类型获取日、周、月榜单（注意，这是不同的表获取数据）
	 * @param type(0:日，1：周，2：月）
	 * @param count
	 * @return
	 */
	public List<MemberInviteStasticRank> topInviteCountByType(int type, int count) {
		return memberInviteStasticRankDao.getLastedRankByType(type, count);
	}

	/**
	 * 排名列表（后台管理使用）
	 * @param imVO
	 * @return
	 */
	public Page<MemberInviteStastic> queryCondition(MemberInviteStasticVO imVO) {
		Criteria<MemberInviteStastic> criteria = new Criteria<>();
		Sort sort = null;
		if(imVO.getRankType().intValue() == 0) {
			sort = criteria.sort("levelOne.desc");
		}
		if(imVO.getRankType().intValue() == 1) {
			sort = criteria.sort("estimatedReward.desc");
		}

		if(StringUtils.isNotEmpty(imVO.getMobilePhone())){
			criteria.add(Restrictions.eq("userIdentify", imVO.getMobilePhone(), false));
		}

		PageRequest pageRequest = PageRequest.of(imVO.getPageNo() - 1, imVO.getPageSize(), sort);
		return memberInviteStasticDao.findAll(criteria,pageRequest);
	}
}
