package cn.muses.trade.service;

import cn.muses.trade.dao.MemberLevelDao;
import cn.muses.trade.entity.MemberLevel;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/12/26 17:26
 */
@Service
public class MemberLevelService extends BaseService {
    @Autowired
    private MemberLevelDao memberLevelDao;

    @Override
    public List<MemberLevel> findAll(){
        return memberLevelDao.findAll();
    }

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description id查询一个
     * @date 2019/12/27 10:54
     */
    public MemberLevel getOne(Long id){
        return  memberLevelDao.getOne(id);
    }

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description 查询默认会员的等级
     * @date 2019/12/26 17:58
     */
    public MemberLevel findDefault() {
        return memberLevelDao.findOneByIsDefault(true);
    }

    /**
     * @author Hevin  E-mail:bizzanhevin@gmail.com
     * @description 更新状态为false 不包括
     * @date 2019/12/27 11:02
     */
    public int updateDefault() {
       return memberLevelDao.updateDefault();
    }

    public MemberLevel save(MemberLevel memberLevel) {
        return memberLevelDao.save(memberLevel);
    }
}
