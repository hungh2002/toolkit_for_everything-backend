package com.ryu.toolkit_for_everything.services.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ryu.toolkit_for_everything.dto.authDTO.AuthDTO;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service("signIn")
public class SignInService implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User execute(AuthDTO signUpDTO) {

        User user = userRepository.findByEmail(signUpDTO.getEmail());

        if (user != null && signUpDTO.getPassword().equals(user.getPassword())) {
            return user;
        } else {
            throw new EntityNotFoundException("Email or password incorrect.");
        }
    }

}
