package com.practicaltask247.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UsersDTO {

    private Long id;
    private String name;
    @Email(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message = "Enter valid email id")
    private String email;
    private String password;

}
