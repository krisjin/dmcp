package com.hx.dmcp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.constant.AdminConstant;
import com.hx.dmcp.dao.UserDao;
import com.hx.dmcp.entity.User;
import com.hx.dmcp.entity.vo.PageVo;
import com.hx.dmcp.util.MD5Util;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(String email, String name, String password, AdminConstant.Status status) {
        email = email.toLowerCase();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setStatus(status);
        user.setCreateTime(new Date());
        user.setPassword(MD5Util.encrypt(password));
        userDao.addUser(user);
        return user;
    }


    public int deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }


    public User updateUser(long userId, String name, String password, AdminConstant.Status status, String email) {
        User user = this.getUserById(userId);
        user.setName(name);
        user.setPassword(MD5Util.encrypt(password));
        user.setEmail(email);
        user.setStatus(status);
        userDao.updateUser(user);
        return user;
    }


    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getAllList(long offset, long rows) {
        return userDao.getAllList(offset, rows);
    }

    public int getAllListCount() {
        return userDao.getAllListCount();
    }

    public PageVo<User> getAllListPage(int pageNum) {
        PageVo<User> pageVo = new PageVo<User>(pageNum);
        pageVo.setRows(5);
        List<User> list = this.getAllList(pageVo.getOffset(), pageVo.getRows());
        pageVo.setList(list);
        pageVo.setCount(this.getAllListCount());
        return pageVo;
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public User getUserByPassword(String password) {
        return userDao.getUserByPassword(password);
    }
}
