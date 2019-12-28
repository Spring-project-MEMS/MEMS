package com.fixit.areas.user.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.fixit.areas.user.models.service.UserServiceModel;

public interface UserService extends UserDetailsService {
    void createUser(UserServiceModel userServiceModele);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UserServiceModel findByUsername(String username);
}