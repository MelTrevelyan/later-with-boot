package com.example.laterwithboot.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private UserState state;
}
