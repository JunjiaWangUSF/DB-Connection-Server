package com.example.dbconnectionserver.service;

import com.example.dbconnectionserver.response.UserRest;

public interface UsersService {
    UserRest getUserDetails(String userName, String password);
    UserRest getUserDetails(String userName);
}
