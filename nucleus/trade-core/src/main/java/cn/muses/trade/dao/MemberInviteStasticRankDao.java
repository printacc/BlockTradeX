package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MemberInviteStasticRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInviteStasticRankDao extends JpaRepository<MemberInviteStasticRank,String> {

	MemberInviteStasticRank findByMemberId(Long memberId);
	
	MemberInviteStasticRank findById(Long id);
	
	/**
	 * 获取最新排名
	 * @param type 0:日榜  1：周榜  2：月榜
	 * @param count 排名前N个
	 * @return
	 */
	@Query(value = "select * from member_invite_stastic_rank where type = :type order by stastic_date desc, level_one desc limit :count", nativeQuery = true)
	List<MemberInviteStasticRank> getLastedRankByType(@Param("type") int type, @Param("count") int count);
}
