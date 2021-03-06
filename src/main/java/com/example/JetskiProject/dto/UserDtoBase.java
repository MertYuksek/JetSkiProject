package com.example.JetskiProject.dto;

import com.example.JetskiProject.utility.customvalidator.ContactNumberConstraint;
import com.example.JetskiProject.utility.customvalidator.NotEmptyOrSpaceOrNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public abstract class UserDtoBase {

    @NotEmptyOrSpaceOrNull(message = "First name cannot be empty.")
    String firstName;

    @NotEmptyOrSpaceOrNull(message = "Last name cannot be empty.")
    String lastName;

    @NotEmpty(message = "Phone number cannot be empty.")
    @ContactNumberConstraint
    String phoneNumber;

    @NotNull(message = "Birth date is mandatory.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy") // In USA In general dd-mm-yyyy
    LocalDate birthDate;
}
