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

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .registrationDate(LocalDateTime.ofInstant(user.getRegistrationDate(), timeZone).format(TIME_FORMATTER))
                .state(user.getState())
                .build();
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
