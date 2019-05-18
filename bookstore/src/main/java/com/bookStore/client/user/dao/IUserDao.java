package com.bookStore.client.user.dao;

import com.bookStore.commons.beans.User;

import java.util.Map;

public interface IUserDao {

    public int addUser(User user);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User loginUser(User user);

    int modifyuserinfo(User user);

    User findUserByLogin(User user);
}
