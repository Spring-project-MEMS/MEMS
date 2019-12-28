package com.fixit.areas.user.services;

import com.fixit.areas.role.entities.Role;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.areas.user.entities.User;
import com.fixit.areas.user.models.binding.UserRegisterBindingModel;
import com.fixit.areas.user.models.service.UserServiceModel;
import com.fixit.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }

    @Override
    public void createUser(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);

        RoleServiceModel roleServiceModel = this.roleService.findByAuthority(userServiceModel.isAdmin() ? "ADMIN" : "USER");
        Role role  = this.modelMapper.map(roleServiceModel, Role.class);

        userEntity.addRole(role);

        this.userRepository.save(userEntity);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return this.userRepository.findOneByUsername(username) != null;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User userEntity = this.userRepository.findOneByUsername(username);
        return this.modelMapper.map(userEntity, UserServiceModel.class);
    }
}