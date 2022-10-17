package com.example.springjwt.service.data_access.impl;

import com.example.springjwt.dto.UserChangePasswordDTO;
import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.entity.AppUser;
import com.example.springjwt.entity.UserRole;
import com.example.springjwt.repository.UserRepo;
import com.example.springjwt.service.data_access.RoleInfoService;
import com.example.springjwt.service.data_access.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserRepo userRepo;
    private final RoleInfoService roleInfoService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(UserDTO userDTO) throws Exception {

        if (userDTO.getEmail().isEmpty()) throw new Exception("Email is empty");
        if (userDTO.getName().isEmpty()) throw new Exception("Name is empty");
        if (userDTO.getPassword().isEmpty()) throw new Exception("Password is empty");
        if (userDTO.getRole().isEmpty()) throw new Exception("User Role is empty");
        System.out.println(userDTO.getRole());
        UserRole userRole = roleInfoService.getUserRoleByRoleName(userDTO.getRole());
        AppUser existingUser = userRepo.findByEmail(userDTO.getEmail());

        if (existingUser != null) throw new Exception("User already exists with given email");

        if (userRole.getUserRoleName().isEmpty()) throw new Exception("User role not found in the DB");

        AppUser appUser = AppUser.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .userRole(userRole)
                .build();

        return userRepo.save(appUser);

    }

    @Override
    public AppUser getUser(String email) throws Exception {

        if (email.isEmpty()) throw new Exception("Email is empty");

        return userRepo.findByEmail(email);
    }

    @Override
    public AppUser removeUser(String email) throws Exception {
        if (email.isEmpty()) throw new Exception("Email is empty");

        AppUser appUser = userRepo.findByEmail(email);

        if (appUser == null) throw new Exception("User not found from given email");

        userRepo.deleteById(appUser.getId());

        return appUser;

    }

    @Override
    public List<AppUser> getUsers() throws Exception {
        return userRepo.findAll();
    }

    @Override
    public AppUser changePassword(UserChangePasswordDTO userChangePasswordDTO) throws Exception {

        AppUser user = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user == null) throw new UsernameNotFoundException("User not found");

        if (!passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new Exception("Password not matching");
        }

        String encodedPassword = passwordEncoder.encode(userChangePasswordDTO.getNewPassword());
        user.setPassword(encodedPassword);

        return userRepo.save(user);

    }
}
