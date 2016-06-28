package dao;


import dao.exception.UserDataIncorrectException;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import validation.UserValidator;

import java.util.List;

@Repository("hibernateUserDao")
public class HibernateUserDao extends CustomHibernateDaoSupport implements UserDao {
    @Autowired
    private UserValidator userValidate;

    @Override
    public void create(User user) {
        if (!userValidate.validate(user)) {
            throw new UserDataIncorrectException();
        }
        try {
            getHibernateTemplate().save(user);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void update(User user) {
        if (!userValidate.validate(user)) {
            throw new UserDataIncorrectException();
        }
        try {
            getHibernateTemplate().update(user);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void remove(User user) {
        try {
            getHibernateTemplate().delete(user);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List objects = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from User");
            objects = query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return objects;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from User where login=:login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
        } finally {
            sessionClose(session);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from User where email=:email");
            query.setParameter("email", email);
            user = (User) query.uniqueResult();
        } finally {
            sessionClose(session);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from User where id=:id");
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
        } finally {
            sessionClose(session);
        }
        return user;
    }

}
