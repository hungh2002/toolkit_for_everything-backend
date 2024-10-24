package com.ryu.toolkit_for_everything.controller;

import org.springframework.web.bind.annotation.RestController;
import com.ryu.toolkit_for_everything.dto.authDTO.SignInDTO;
import com.ryu.toolkit_for_everything.dto.authDTO.SignUpDTO;
import com.ryu.toolkit_for_everything.services.authServices.AuthService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

    @Autowired
    @Qualifier("signUp")
    AuthService signUpService;

    @Autowired
    @Qualifier("signIn")
    AuthService signInService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            signUpService.execute(signUpDTO);
            return ResponseEntity.ok("Sign up successfully");
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signIpDTO) {
        try {
            signInService.execute(signIpDTO);
            return ResponseEntity.ok("Sign in successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }


}
