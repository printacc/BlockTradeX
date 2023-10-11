package cn.muses.trade.service;

import cn.muses.trade.dao.SysPermissionDao;
import cn.muses.trade.entity.SysPermission;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年12月20日
 */
@Service
public class SysPermissionService extends BaseService<SysPermission> {

    @Autowired
    public void setDao(SysPermissionDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private SysPermissionDao sysPermissionDao;

    public SysPermission getOne(Long id) {
        return sysPermissionDao.getOne(id);
    }

    @Override
    public List<SysPermission> findAll() {
        return sysPermissionDao.findAll();
    }

    public SysPermission save(SysPermission sysPermission) {
        return sysPermissionDao.save(sysPermission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletes(Long[] ids) {
        for (long id : ids) {
            sysPermissionDao.deletePermission(id);
            sysPermissionDao.deleteById(id);
        }
    }

    public Page<SysPermission> findAll(Predicate predicate, Pageable pageable) {
        return sysPermissionDao.findAll(predicate, pageable);
    }
}
