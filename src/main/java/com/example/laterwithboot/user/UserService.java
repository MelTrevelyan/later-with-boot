package com.example.laterwithboot.user;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto saveUser(UserDto userDto);
    UserDto findUserById(long id);
}
