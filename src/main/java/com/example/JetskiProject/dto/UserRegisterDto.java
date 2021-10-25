package com.example.JetskiProject.dto;

import com.example.JetskiProject.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRegisterDto extends UserDtoBase{

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 10, message = "Password must be 8-10 characters long.")
    private String password;

    @NotEmptyOrSpaceOrNull(message = "Email address cannot be empty.")
    @Email(message = "It is not convenient email address.")
    String emailAddress;
}
