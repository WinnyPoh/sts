package service;

import model.User;

import java.util.List;

public interface UserService {
    public void create(User user);
    public void update(User user);
    public void remove(User user);
    public List<User> findAll();
    public User findByLogin(String login);
    public User findByEmail(String email);
    public User findById(Long id);
}
