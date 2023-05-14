package com.example.laterwithboot.user;

import com.example.laterwithboot.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.toUserDtos(repository.findAll());
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(repository.save(user));
    }

    @Override
    public UserDto findUserById(long id) {
        return UserMapper.toUserDto(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
