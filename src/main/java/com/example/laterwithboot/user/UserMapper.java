package com.example.laterwithboot.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UserMapper {
    public static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss");

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
}
