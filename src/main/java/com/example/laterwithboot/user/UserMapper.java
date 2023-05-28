package com.example.laterwithboot.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss");

    public static UserDto toUserDto(User user) {
        ZoneId timeZone = ZoneId.systemDefault();
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRegistrationDate(LocalDateTime.ofInstant(user.getRegistrationDate(), timeZone).format(TIME_FORMATTER));
        userDto.setState(user.getState());

        return userDto;
    }

    public static List<UserDto> toUserDtos(Collection<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(toUserDto(user));
        }
        return dtos;
    }

    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getFirstName(),
                userDto.getLastName(), userDto.getState());
    }
}
