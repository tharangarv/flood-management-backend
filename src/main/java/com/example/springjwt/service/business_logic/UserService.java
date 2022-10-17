package com.example.springjwt.service.business_logic;

import com.example.springjwt.dto.UserChangePasswordDTO;
import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.dto.UserListDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO getUser(String email);

    UserDTO removeUser(String email);

    UserListDTO getUsers();

    UserDTO changePassword(UserChangePasswordDTO userChangePasswordDTO);

}
