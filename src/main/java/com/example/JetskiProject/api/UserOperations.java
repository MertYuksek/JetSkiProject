package com.example.JetskiProject.api;

import com.example.JetskiProject.dto.UserRegisterDto;
import com.example.JetskiProject.dto.UserUpdateDto;
import com.example.JetskiProject.model.User;
import com.example.JetskiProject.services.UserService;
import com.example.JetskiProject.utility.customvalidator.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/userOps")
@RequiredArgsConstructor
public class UserOperations {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> userRegister(@Valid @RequestBody UserRegisterDto userRegisterDto){
        Response<User> response = userService.saveUser(userRegisterDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getMessage());
    }

    @PostMapping(value = "/update")
    public ResponseEntity<String> userUpdate(@Valid @RequestBody UserUpdateDto userUpdateDto){
        Response<User> response = userService.updateUser(userUpdateDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getMessage());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> userDelete(@RequestParam(value = "userId") Long userId){
        Response<User> response = userService.deleteUser(userId);
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getMessage());
    }

    @GetMapping("/get")
    public ResponseEntity<?> userGet(@RequestParam(value = "userId") Long userId){
        Response<User> response = userService.findUser(userId);
        if(response.getStatus() != HttpStatus.OK){
            return ResponseEntity
                    .status(response.getStatus())
                    .body(response.getMessage());
        }
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getOptionalT().get());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
