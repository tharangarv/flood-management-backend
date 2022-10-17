package com.example.springjwt.service.data_access;

import com.example.springjwt.dto.UserChangePasswordDTO;
import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.entity.AppUser;

import java.util.List;

public interface UserInfoService {

    AppUser saveUser(UserDTO setUserDTO) throws Exception;

    AppUser getUser(String email) throws Exception;

    AppUser removeUser(String email) throws Exception;

    List<AppUser> getUsers() throws Exception;

    AppUser changePassword(UserChangePasswordDTO userChangePasswordDTO) throws Exception;

}
