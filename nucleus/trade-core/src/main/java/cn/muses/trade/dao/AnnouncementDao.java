package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Announcement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/3/5 15:32
 */
public interface AnnouncementDao extends BaseDao<Announcement> {

    @Query("select max(s.sort) from Announcement s")
    int findMaxSort();


    /**
     * 获取公告上一条
     * @param annId
     * @return
     */
    @Query(value = "select * from announcement where id<:annId AND is_show=1 AND lang=:lang ORDER by id desc limit 0,1",nativeQuery = true)
    Announcement getBack(@Param("annId") long annId, @Param("lang") String lang);

    /**
     * 获取公告下一条
     * @param annId
     * @return
     */
    @Query(value = "select * from announcement where id>:annId AND is_show=1 AND lang=:lang limit 0,1",nativeQuery = true)
    Announcement getNext(@Param("annId") long annId, @Param("lang") String lang);
}
