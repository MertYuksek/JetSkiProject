package com.example.JetskiProject.services;

import com.example.JetskiProject.dto.UserRegisterDto;
import com.example.JetskiProject.dto.UserUpdateDto;
import com.example.JetskiProject.utility.customvalidator.Response;

public interface UserService<T> {

    Response<T> saveUser(UserRegisterDto user);
    Response<T> updateUser(UserUpdateDto user);
    Response<T> deleteUser(long userId);
    Response<T> findFirstByEmailAddressEquals(String emailAddress);
    Response<T> findUser(long userId);
}
