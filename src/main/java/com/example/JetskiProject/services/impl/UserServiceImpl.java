package com.example.JetskiProject.services.impl;

import com.example.JetskiProject.dto.UserRegisterDto;
import com.example.JetskiProject.dto.UserUpdateDto;
import com.example.JetskiProject.model.Role;
import com.example.JetskiProject.model.Roles;
import com.example.JetskiProject.model.User;
import com.example.JetskiProject.repo.UserRepository;
import com.example.JetskiProject.services.RoleService;
import com.example.JetskiProject.services.UserService;
import com.example.JetskiProject.utility.customvalidator.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public Response<User> saveUser(UserRegisterDto userRegisterDto) {

        User user = modelMapper.map(userRegisterDto,User.class);

        Optional<User> userTemp =  findFirstByEmailAddressEquals
                (user.getEmailAddress()).getOptionalT();

        if(!userTemp.isEmpty()){
            if(userTemp.get().isDeleted()){
                return new Response<>(
                        "This user email account is disabled.",
                        HttpStatus.FORBIDDEN);
            }
            else {
                return new Response<>(
                        "There is a registered user with this email address.",
                        HttpStatus.BAD_REQUEST);
            }
        }
        else {
            Role role = roleService.findRoleByName(Roles.ROLE_USER);
            user.setRole(role);
            userRepository.save(user);
            return new Response<>(
                    "Registration is successful",
                             HttpStatus.OK);
        }
    }

    @Override
    public Response<User> updateUser(UserUpdateDto userUpdateDto) {

        Optional<User> user = findUser(userUpdateDto.getUserId()).getOptionalT();

        if(user.isEmpty()){
            return new Response<>(
                    "User is not found.",
                    HttpStatus.NOT_FOUND);
        }
        else if(user.get().isDeleted()){
            return new Response<>(
                    "User is disabled.",
                    HttpStatus.FORBIDDEN);
        }
        else{
            User userTemp = modelMapper.map(userUpdateDto, User.class);
            userTemp.setRole(user.get().getRole());
            userTemp.setPassword(user.get().getPassword());
            userTemp.setEmailAddress(user.get().getEmailAddress());
            userRepository.save(userTemp);
            return new Response<>(
                    "Update operation is successful",
                            HttpStatus.OK);
        }
    }

    @Override
    public Response<User> deleteUser(long userId) {
        Response<User> response = findUser(userId);
        if(response.getOptionalT().isEmpty() ||
                response.getOptionalT().get().isDeleted()){
            return response;
        }
        else {
            User user = response.getOptionalT().get();
            user.setDeleted(true);
            userRepository.save(user);
            return new Response<>(
                    "User is deleted",
                            HttpStatus.OK);
        }
    }

    @Override
    public Response<User> findUser(long userId) {

        Optional<User> user = userRepository.findById(userId);
        return customUserFind(user);
    }

    @Override
    public Response<User> findFirstByEmailAddressEquals(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        return customUserFind(user);
    }

    private Response<User> customUserFind(Optional<User> user){
        if(user.isEmpty()){
            return new Response<>(
                    user,
                    "User not found.",
                    HttpStatus.NOT_FOUND);
        }
        else if(user.get().isDeleted()){
            return new Response<>(
                    user,
                    "No further information is to be supplied",
                    HttpStatus.FORBIDDEN);
        }
        else {
            return new Response<>(
                    user,
                    "User is found.",
                    HttpStatus.OK);
        }
    }
}
