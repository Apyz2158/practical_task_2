package com.practicaltask247.user;

import com.practicaltask247.config.CommonResponse;
import com.practicaltask247.user.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse login(@RequestBody UsersDTO user) {
        return usersService.login(user);
    }

    @GetMapping("/user/create")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse createUsers() {
        return usersService.createUsers();
    }

}
