package com.bookStore.client.user.service;

import com.bookStore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    int addUser(User user, HttpServletRequest request);

    int activeUer(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User loginUser(User user);

    int modifyuserinfo(User user);

    User findUserByLogin(User user);
}
