package com.academy.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
