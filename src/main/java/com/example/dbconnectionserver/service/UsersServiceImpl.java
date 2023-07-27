package com.example.dbconnectionserver.service;

import com.example.dbconnectionserver.data.UserEntity;
import com.example.dbconnectionserver.data.UsersRepository;
import com.example.dbconnectionserver.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserRest getUserDetails(String userName) {
        UserRest returnValue = new UserRest();

        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (userEntity == null) {
            return returnValue;
        }

        //only copy features from UserRest
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserRest getUserDetails(String userName, String password) {
        UserRest returnValue = null;

        UserEntity userEntity = usersRepository.findByEmail(userName);

        if (userEntity == null) {
            return returnValue;
        }

        if (bCryptPasswordEncoder.matches(password,
                userEntity.getEncryptedPassword())) {
            System.out.println("password matches!!!");

            returnValue = new UserRest();
            BeanUtils.copyProperties(userEntity, returnValue);

        }

        return returnValue;
    }

}
