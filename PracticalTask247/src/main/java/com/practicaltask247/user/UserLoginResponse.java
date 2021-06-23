package com.practicaltask247.user;

import com.practicaltask247.user.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponse {

    private UsersDTO usersDTO;
    private String token;
    private String refreshToken;

}
