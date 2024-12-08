package com.ryu.toolkit_for_everything.services.authServices;

import com.ryu.toolkit_for_everything.dto.authDTO.SignInDTO;
import com.ryu.toolkit_for_everything.dto.authDTO.SignUpDTO;
import com.ryu.toolkit_for_everything.entity.User;

public interface AuthService {
    public User signIn(SignInDTO signInDTO);

    public void signUp(SignUpDTO signUpDTO);

}
