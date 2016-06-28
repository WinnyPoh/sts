package dao;

import model.Permission;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("hibernatePermissionDao")
public class HibernatePermissionDao extends CustomHibernateDaoSupport implements PermissionDao {

    @Override
    public void create(Permission permission) {
        try {
            getHibernateTemplate().save(permission);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void update(Permission permission) {
        try {
            getHibernateTemplate().update(permission);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void remove(Permission permission) {
        try {
            getHibernateTemplate().delete(permission);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public Permission findByName(String name) {
        Permission permission = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from Permission where name=:name");
            query.setParameter("name", name);
            permission = (Permission) query.uniqueResult();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            sessionClose(session);
        }
        return permission;
    }
}
