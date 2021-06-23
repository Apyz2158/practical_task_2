package com.practicaltask247.user;

import com.practicaltask247.config.CommonResponse;
import com.practicaltask247.user.dto.UsersDTO;

public interface UsersService {

    CommonResponse createUsers();

    CommonResponse login(UsersDTO usersDTO);

}
