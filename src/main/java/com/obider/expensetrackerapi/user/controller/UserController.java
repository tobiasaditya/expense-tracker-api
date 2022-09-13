package com.obider.expensetrackerapi.user.controller;

import com.obider.expensetrackerapi.response.ResponseHandler;
import com.obider.expensetrackerapi.security.Security;
import com.obider.expensetrackerapi.user.service.UserService;
import com.obider.expensetrackerapi.user.entity.User;
import com.obider.expensetrackerapi.user.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Map<String,Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = new User(firstName,lastName,email,password);
        try{
            userService.registerUser(user);
            String token = new Security().generateJWTToken(user);
            Map<String,String> data = new HashMap<>();
            data.put("token",token);
            return ResponseHandler.generateResponse("Success create",HttpStatus.OK,data);
        } catch (RuntimeException err){
            return ResponseHandler.generateResponse(err.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginUser loginUser){
        try{
            User user = userService.validateUser(loginUser.getEmail(),loginUser.getPassword());
            String token = new Security().generateJWTToken(user);
            Map<String,String> data = new HashMap<>();
            data.put("token",token);
            return ResponseHandler.generateResponse("Login success",HttpStatus.OK,data);
        } catch (Exception err){
            return ResponseHandler.generateResponse(err.getMessage(),HttpStatus.UNAUTHORIZED);
        }

    }
}
