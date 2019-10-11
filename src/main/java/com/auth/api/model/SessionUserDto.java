package com.auth.api.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class SessionUserDto {

    private String email;

    private String firstName;

    private String lastName;

    private String idCode;

    private Date birthday;

}
