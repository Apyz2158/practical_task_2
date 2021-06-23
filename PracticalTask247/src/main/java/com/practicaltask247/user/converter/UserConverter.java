package com.practicaltask247.user.converter;

import com.practicaltask247.user.Users;
import com.practicaltask247.user.dto.UsersDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UsersDTO convert(Users users){
        UsersDTO dto  = new UsersDTO();
        dto.setId(users.getId());
        dto.setName(users.getName());
        dto.setEmail(users.getEmail());
        return dto;
    }
}
