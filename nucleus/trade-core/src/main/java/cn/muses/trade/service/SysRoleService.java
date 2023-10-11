package cn.muses.trade.service;

import cn.muses.trade.core.Menu;
import cn.muses.trade.dao.AdminDao;
import cn.muses.trade.dao.SysPermissionDao;
import cn.muses.trade.dao.SysRoleDao;
import cn.muses.trade.entity.Admin;
import cn.muses.trade.entity.SysPermission;
import cn.muses.trade.entity.SysRole;
import cn.muses.trade.service.Base.TopBaseService;
import cn.muses.trade.util.MessageResult;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年12月18日
 */
@Service
public class SysRoleService extends TopBaseService<SysRole, SysRoleDao> {
    @Autowired
    private LocaleMessageSourceService msService;

    @Autowired
    private AdminService adminService;

    @Override
    @Autowired
    public void setDao(SysRoleDao dao) {
        super.setDao(dao);
    }

    @Resource
    private SysRoleDao sysRoleDao;

    @Autowired
    private AdminDao adminDao;
    @Resource
    private SysPermissionDao sysPermissionDao;

    public SysRole getOne(Long id) {
        SysRole role = sysRoleDao.getOne(id);
        return role;
    }

    public List<SysPermission> getPermissions(Long roleId) {
        SysRole sysRole = getOne(roleId);
        List<SysPermission> list = sysRole.getPermissions();
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageResult deletes(Long id) {
        List<Admin> list = adminDao.findAllByRoleId(id);
        if (list != null && list.size() > 0) {
            return MessageResult.error(msService.getMessage("FAIL"));
        }
        sysRoleDao.deleteById(id);
        return MessageResult.success(msService.getMessage("SUCCESS"));
    }

    /**
     * 把权限转换成菜单树
     *
     * @param sysPermissions
     * @param parentId
     * @return
     */
    public List<Menu> toMenus(List<SysPermission> sysPermissions, Long parentId) {
        return sysPermissions.stream()
                .filter(x -> x.getParentId().equals(parentId))
                .sorted(Comparator.comparing(SysPermission::getSort))
                .map(x ->
                        Menu.builder()
                                .id(x.getId())
                                .name(x.getName())
                                .parentId(x.getParentId())
                                .sort(x.getSort())
                                .title(x.getTitle())
                                .titleKey(x.getTitleKey())
                                .description(x.getDescription())
                                .subMenu(toMenus(sysPermissions, x.getId()))
                                .build()

                )
                .collect(Collectors.toList());
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleDao.save(sysRole);
    }

    public int updateDetail(SysRole sysRole) {
        return sysRoleDao.updateSysRole(sysRole.getDescription(), sysRole.getRole(), sysRole.getId());
    }

    public List<SysPermission> getAllPermission() {
        return sysPermissionDao.findAll();
    }

    public List<SysRole> getAllSysRole() {
        return sysRoleDao.findAllSysRole();
    }

    public Page<SysRole> findAll(Predicate predicate, Pageable pageable) {
        return sysRoleDao.findAll(predicate, pageable);
    }
}
