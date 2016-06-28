package service;

import dao.HibernatePermissionDao;
import model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private HibernatePermissionDao hibernatePermissionDao;

    @Override
    @Transactional
    public void create(Permission permission) {
        hibernatePermissionDao.create(permission);
    }

    @Override
    @Transactional
    public void update(Permission permission) {
        hibernatePermissionDao.update(permission);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        hibernatePermissionDao.remove(permission);
    }

    @Override
    public Permission findByName(String name) {
        return hibernatePermissionDao.findByName(name);
    }
}
