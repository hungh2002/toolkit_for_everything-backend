package com.ryu.toolkit_for_everything.controller;

import com.ryu.toolkit_for_everything.dto.authDTO.SignInDTO;
import com.ryu.toolkit_for_everything.dto.authDTO.SignUpDTO;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.services.authServices.AuthService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Log4j2
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@ModelAttribute SignUpDTO signUpDTO) {
        try {
            authService.signUp(signUpDTO);
            return ResponseEntity.ok("Sign up successfully");
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInDTO signInDTO) {
        try {
            User user = authService.signIn(signInDTO);

            Map<String, String> data = new HashMap<>();
            data.put("userId", String.valueOf(user.getId()));
            data.put("userName", user.getName());

            return ResponseEntity.ok(data);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }
}
