/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.service;

import com.werapan.databaseproject.dao.UserDao;
import com.werapan.databaseproject.model.User;
import java.util.List;

/**
 *
 * @author werapan
 */
public class UserService {
    public static User currentUser;
    public User login(String login, String password) {
        UserDao userDao = new UserDao();
        User user = userDao.getByLogin(login);
        if(user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public  User getCurrentUser() {
        UserDao userDao = new UserDao();
        User user = userDao.get(6);;
        return user;
    }
    
    public int deleteUser(User editedUser) {
        UserDao userdao = new UserDao();
        return userdao.delete(editedUser);
    }
    public List<User> getUsers(){
        UserDao userDao = new UserDao();
        return userDao.getAll(" user_login asc");
    }
    public List<User> getuser() {
        UserDao userDao = new UserDao();
        return userDao.getAll("user_login asc");

    }
    public User updateUser(User editedUser) {
        UserDao userDao = new UserDao();
        return userDao.update(editedUser);
    }
    public User addNew(User editedUser) {
        UserDao userDao = new UserDao();
        return userDao.save(editedUser);
    }

    public User update(User editedUser) {
        UserDao userDao = new UserDao();
        return userDao.update(editedUser);
    }

    public int delete(User editedUser) {
        UserDao userDao = new UserDao();
        return userDao.delete(editedUser);
    }
}
