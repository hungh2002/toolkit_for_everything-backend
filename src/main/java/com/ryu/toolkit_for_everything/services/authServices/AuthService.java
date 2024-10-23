package com.ryu.toolkit_for_everything.services.authServices;

import com.ryu.toolkit_for_everything.dto.authDTO.AuthDTO;
import com.ryu.toolkit_for_everything.entity.User;

public interface AuthService {
    public User execute(AuthDTO signUpDTO);
}
