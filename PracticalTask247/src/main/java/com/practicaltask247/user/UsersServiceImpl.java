package com.practicaltask247.user;

import com.practicaltask247.config.CommonResponse;
import com.practicaltask247.config.CustomUsersDetails;
import com.practicaltask247.constants.StringConstants;
import com.practicaltask247.exception.GeneralException;
import com.practicaltask247.user.converter.UserConverter;
import com.practicaltask247.user.dto.UsersDTO;
import com.practicaltask247.utilities.CommonUtilities;
import com.practicaltask247.utilities.JwtTokenUtil;
import com.practicaltask247.utilities.ResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Value("${jwt.secret.accessToken}")
    private String accessSecret;
    @Value("${jwt.secret.refreshToken}")
    private String refreshSecret;
    @Value("${jwt.accessToken.validity}")
    private long accessTokenValidity;
    @Value("${jwt.refreshToken.validity}")
    private long refreshTokenValidity;

    private JwtTokenUtil jwtTokenUtil;
    private UserConverter userConverter;
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(JwtTokenUtil jwtTokenUtil, UserConverter userConverter, UsersRepository usersRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userConverter = userConverter;
        this.usersRepository = usersRepository;
    }

    @Override
    public CommonResponse createUsers() {
        Users user01 = new Users();
        user01.setName("Arpit Chaudhari");
        user01.setEmail("arpit@mail.com");
        user01.setPassword(CommonUtilities.getEncryptedPassword("Arpit@18"));

        Users user02 = new Users();
        user02.setName("Chirag Mehta");
        user02.setEmail("chirag@mail.com");
        user02.setPassword(CommonUtilities.getEncryptedPassword("Chirag@18"));

        List<Users> userList = List.of(user01, user02);
        List<Users> savedUsers = new ArrayList<>();
        try {
            savedUsers = usersRepository.saveAll(userList);
        } catch (Exception e) {
            throw new GeneralException(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }
        for(int i=0;i<userList.size();i++){
            Users u = userList.get(i);
            if(i==0){
                u.setPassword("Arpit@18");
            } else {
                u.setPassword("Chirag@18");
            }
        }
        CommonResponse response = new CommonResponse(userList);
        return ResponseUtilities.createSucessResponseWithMessage(response, StringConstants.USERS_CREATED_SUCCESSFULLY);
    }

    @Override
    public CommonResponse login(UsersDTO usersDTO) {
            Users user = usersRepository.findByEmailAndPassword(usersDTO.getEmail(), CommonUtilities.getEncryptedPassword(usersDTO.getPassword()));
        if (user == null) {
            user = usersRepository.findByEmail(usersDTO.getEmail());
            if (user == null) {
                throw new GeneralException("Username and password does not matched", HttpStatus.BAD_REQUEST);
            }
            throw new GeneralException("User not found with username " + usersDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        CustomUsersDetails userDetails = new CustomUsersDetails(user);
        String token = jwtTokenUtil.generateToken(userDetails.getEmail(), userDetails, accessSecret, accessTokenValidity);
        String refreshToken = jwtTokenUtil.generateToken(userDetails.getEmail(), userDetails, accessSecret, accessTokenValidity);
        UserLoginResponse loginResponse = new UserLoginResponse(userConverter.convert(user), token, refreshToken);
        CommonResponse response = new CommonResponse(loginResponse);
        return ResponseUtilities.createSucessResponseWithMessage(response, StringConstants.LOGIN_SUCCESSFULLY);
    }

}
