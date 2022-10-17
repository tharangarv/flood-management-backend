package com.example.springjwt.controller;

import com.example.springjwt.dto.*;
import com.example.springjwt.service.business_logic.RoleService;
import com.example.springjwt.service.business_logic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable("email") String email
    ) {
        UserDTO userDTO = userService.getUser(email);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(
            @RequestBody UserDTO userDTO
    ) {

        UserDTO savedUser = userService.saveUser(userDTO);

        if (savedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserDTO> removeUser(
            @PathVariable("email") String email
    ) {

        UserDTO deletedUser = userService.removeUser(email);

        if (deletedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deletedUser, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<UserListDTO> getUsers() {

        UserListDTO users = userService.getUsers();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping("/change_password")
    public ResponseEntity<UserDTO> changePassword(
            @RequestBody UserChangePasswordDTO userChangePasswordDTO
    ) {
        UserDTO user = userService.changePassword(userChangePasswordDTO);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    todo:edit user

    
//    ROLES

    @GetMapping("/role")
    public ResponseEntity<UserRoleListDTO> getRoles() {

        UserRoleListDTO roles = roleService.getRole();

        if (roles == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @PostMapping("/role")
    public ResponseEntity<RoleDTO> saveRole(
            @RequestBody RoleDTO roleDTO
    ) {

        RoleDTO savedRole = roleService.saveRole(roleDTO);

        if (savedRole == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);

    }

}
