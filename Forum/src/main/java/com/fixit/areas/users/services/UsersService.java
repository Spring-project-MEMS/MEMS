package com.fixit.areas.users.services;

import com.fixit.areas.users.models.service.UsersServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    void createUser(UsersServiceModel userServiceModel);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UsersServiceModel findByUsername(String username);
}
