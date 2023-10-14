package com.authentication.login.controller;

import com.authentication.login.model.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8082"})

public class LoginController {

    private List<Login> users = List.of(new Login("bsherhj360@gmail.com", "123"), new Login("ahmad@gmail.com", "ahmad123"));

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {

        Map<String, String> response = new HashMap<>();

        if (users.contains(login)) {
            response.put("message", "Authentication successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
