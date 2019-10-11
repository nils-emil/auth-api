package com.auth.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "auth_user")
public class SessionUser {

    @Id
    @Column
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String idCode;

    @Column
    private String userRole;

    @Column
    @NotNull
    private Date birthday;

}
