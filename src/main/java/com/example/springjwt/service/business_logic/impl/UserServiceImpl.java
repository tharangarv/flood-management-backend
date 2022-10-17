package com.example.springjwt.service.business_logic.impl;

import com.example.springjwt.dto.UserChangePasswordDTO;
import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.dto.UserListDTO;
import com.example.springjwt.entity.AppUser;
import com.example.springjwt.repository.UserRepo;
import com.example.springjwt.service.business_logic.UserService;
import com.example.springjwt.service.data_access.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserInfoService userInfoService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail(email);

        if (user == null) {
            log.error("User not found from given email : {}", email);
            throw new UsernameNotFoundException("User not found from given email : " + email);
        }
        log.info("User found from given email : {}", email);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().getUserRoleName());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return new User(user.getEmail(), user.getPassword(), authorities);

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        try {
            AppUser appUser = userInfoService.saveUser(userDTO);
            UserDTO savedUser = UserDTO.builder()
                    .email(appUser.getEmail())
                    .name(appUser.getName())
                    .role(appUser.getUserRole().getUserRoleName())
                    .build();

            log.info("user saved : {}", savedUser);
            return savedUser;

        } catch (Exception e) {
            log.error("Error : Unable to save the user : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO getUser(String email) {

        try {
            AppUser appUser = userInfoService.getUser(email);

            if (appUser == null) {
                log.info("No user found");
                return null;
            }

            UserDTO user = UserDTO.builder()
                    .email(appUser.getEmail())
                    .name(appUser.getName())
                    .role(appUser.getUserRole().getUserRoleName())
                    .build();

            log.info("User : {}", user);
            return user;

        } catch (Exception e) {
            log.error("Error : Unable to get the user : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO removeUser(String email) {
        try {

            AppUser user = userInfoService.removeUser(email);

            UserDTO removedUser = UserDTO.builder()
                    .name(user.getName())
                    .role(user.getUserRole().getUserRoleName())
                    .email(user.getEmail())
                    .build();

            log.info("User : {}", removedUser);
            return removedUser;

        } catch (Exception e) {
            log.error("Error : Unable to delete user : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public UserListDTO getUsers() {
        try {
            List<AppUser> users = userInfoService.getUsers();

            List<UserDTO> convertedUsers = users.stream().map(user -> UserDTO.builder()
                    .name(user.getName())
                    .role(user.getUserRole().getUserRoleName())
                    .email(user.getEmail())
                    .build()).collect(Collectors.toList());

            UserListDTO usersList = UserListDTO.builder()
                    .users(convertedUsers)
                    .build();

            log.info("User : {}", usersList);
            return usersList;

        } catch (Exception e) {
            log.error("Error : Unable to get users : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO changePassword(UserChangePasswordDTO userChangePasswordDTO) {

        try {
            AppUser user = userInfoService.changePassword(userChangePasswordDTO);

            UserDTO userDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .role(user.getUserRole().getUserRoleName())
                    .name(user.getName())
                    .build();

            log.info("Password changed : {}", userDTO);

            return userDTO;

        } catch (Exception e) {
            log.error("Error : Unable to change password : {}", e.getMessage());
            return null;
        }

    }

}
