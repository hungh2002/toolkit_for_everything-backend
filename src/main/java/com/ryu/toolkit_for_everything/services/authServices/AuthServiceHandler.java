package com.ryu.toolkit_for_everything.services.authServices;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ryu.toolkit_for_everything.dto.authDTO.SignInDTO;
import com.ryu.toolkit_for_everything.dto.authDTO.SignUpDTO;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthServiceHandler implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signIn(SignInDTO signInDTO) {
        Optional<User> userOptional = userRepository.findByEmail(signInDTO.getEmail());

        if (userOptional.isPresent()
                && signInDTO.getPassword().equals(userOptional.get().getPassword())) {
            return userOptional.get();
        } else {
            throw new EntityNotFoundException("Email or password incorrect.");
        }
    }

    @Override
    public void signUp(SignUpDTO signUpDTO) {
        if (signUpDTO.getEmail() == null && signUpDTO.getPassword() == null) {
            throw new NullPointerException("Missing email or password");
        }

        // Check if email already exists
        Optional<User> userOptional = userRepository.findByEmail(signUpDTO.getEmail());

        if (!userOptional.isPresent()) {
            User user = new User();
            user.setName(String.valueOf(System.currentTimeMillis()));
            user.setEmail(signUpDTO.getEmail());
            user.setPassword(signUpDTO.getPassword());

            userRepository.save(user);
        } else {
            throw new EntityExistsException("Account already exists");
        }

    }

}
