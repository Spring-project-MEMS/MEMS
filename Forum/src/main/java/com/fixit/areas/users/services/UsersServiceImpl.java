package com.fixit.areas.users.services;

import com.fixit.areas.role.entities.Role;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.repositories.UsersRepository;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.services.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final WardService wardService;

    @Autowired
    public UsersServiceImpl(UsersRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService, WardService wardService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.wardService = wardService;
    }

    @Override
    public void createUser(UsersServiceModel userServiceModel) {
        Users userEntity = this.modelMapper.map(userServiceModel, Users.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);

        if(userServiceModel.getEgn().isEmpty()){
            userEntity.setEgn(null);
        }

        if(userServiceModel.getFirstName().isEmpty()){
            userEntity.setFirstName(null);
        }

        if(userServiceModel.getLastName().isEmpty()){
            userEntity.setLastName(null);
        }

        String authorityName = "PATIENT";
        if(userServiceModel.isAdmin()){
            authorityName = "ADMIN";
        }
        if(userServiceModel.isDoctor()){
            authorityName = "DOCTOR";
        }
        RoleServiceModel roleServiceModel = this.roleService.findByAuthority(authorityName);
        Role role  = this.modelMapper.map(roleServiceModel, Role.class);

        userEntity.addRole(role);
//        if(!userServiceModel.getWardName().isEmpty())
//        {
//            WardServiceModel wardServiceModel = this.wardService.findByWardName(userServiceModel.getWardName());
//            Ward ward = this.modelMapper.map(wardServiceModel, Ward.class);
//
//            userEntity.setWard(ward);
//        }

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
    public UsersServiceModel findByUsername(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);
        return this.modelMapper.map(userEntity, UsersServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }
}
