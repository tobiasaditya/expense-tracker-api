package com.obider.expensetrackerapi.user.service;

import com.obider.expensetrackerapi.user.entity.User;

public interface UserService {
    User validateUser(String email, String password);
    User registerUser(User user);

    User findUserById(Integer userId);
}
