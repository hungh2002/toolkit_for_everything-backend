package com.ryu.toolkit_for_everything.controller;

import com.ryu.toolkit_for_everything.dto.authDTO.SignInDTO;
import com.ryu.toolkit_for_everything.dto.authDTO.SignUpDTO;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.services.authServices.AuthService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    @Qualifier("signUp")
    AuthService signUpService;

    @Autowired
    @Qualifier("signIn")
    AuthService signInService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@ModelAttribute SignUpDTO signUpDTO) {
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
    public ResponseEntity<Object> signIn(@RequestBody SignInDTO signIpDTO) {
        try {
            User user = signInService.execute(signIpDTO);

            Map<String, String> data = new HashMap<String, String>();
            data.put("userId", String.valueOf(user.getId()));
            data.put("password", user.getPassword());

            return ResponseEntity.ok(data);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }


}
