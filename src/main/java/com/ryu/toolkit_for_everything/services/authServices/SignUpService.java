package com.ryu.toolkit_for_everything.services.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ryu.toolkit_for_everything.dto.authDTO.AuthDTO;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.repository.UserRepository;
import jakarta.persistence.EntityExistsException;

@Service("signUp")
public class SignUpService implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User execute(AuthDTO signUpDTO) {

        // Check if email already exists
        User user = userRepository.findByEmail(signUpDTO.getEmail());

        if (user == null) {
            user.setName(String.valueOf(System.currentTimeMillis()));
            user.setEmail(signUpDTO.getEmail());
            user.setPassword(signUpDTO.getPassword());

            return userRepository.save(user);
        } else {
            throw new EntityExistsException("Account already exists");
        }

    }
}
