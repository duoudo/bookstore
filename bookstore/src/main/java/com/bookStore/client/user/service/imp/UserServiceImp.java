package com.bookStore.client.user.service.imp;

import com.bookStore.client.user.dao.IUserDao;
import com.bookStore.client.user.service.UserService;
import com.bookStore.commons.beans.User;
import com.bookStore.utils.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private IUserDao userDao;

    @Override
    public int addUser(User user, HttpServletRequest request) {
//      发送邮件方法
        String emailMsg="请点击<a href='http://localhost:8080"+request.getContextPath()+"/client/user/activeUser.do?activeCode="+user.getActiveCode()+"'>激活</a>后再使用！";
        int row=0;
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
            row = userDao.addUser(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int activeUer(String activeCode) {
        return userDao.activeUser(activeCode);
    }

    @Override
    public User findEmail(String email) {
        return userDao.findEmail(email);
    }

    public User findUsername(String username) {
        return userDao.findUsername(username);
    }
    @Override
    public User loginUser(User user) {
        return userDao.loginUser(user);
    }

    @Override
    public int modifyuserinfo(User user) {
        return userDao.modifyuserinfo(user);
    }

    @Override
    public User findUserByLogin(User user) {
        return userDao.findUserByLogin(user);
    }
}
