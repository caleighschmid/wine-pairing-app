package com.wineapp.dao;

import com.wineapp.model.RegisterUserDto;
import com.wineapp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
