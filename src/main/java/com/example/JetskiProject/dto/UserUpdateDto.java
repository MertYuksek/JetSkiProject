package com.example.JetskiProject.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class UserUpdateDto extends UserDtoBase{

    @Positive
    private long userId;
}
