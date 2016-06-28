package service;

import dao.HibernateUserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    HibernateUserDao hibernateUserDao;

    @Override
    @Transactional
    public void create(User user) {
        hibernateUserDao.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        hibernateUserDao.update(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        hibernateUserDao.remove(user);
    }

    @Override
    public List<User> findAll() {
        return hibernateUserDao.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return hibernateUserDao.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return hibernateUserDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return hibernateUserDao.findById(id);
    }
}
