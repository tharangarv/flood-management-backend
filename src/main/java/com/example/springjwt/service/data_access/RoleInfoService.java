package com.example.springjwt.service.data_access;

import com.example.springjwt.dto.RoleDTO;
import com.example.springjwt.entity.UserRole;

import java.util.List;

public interface RoleInfoService {

    UserRole saveUserRole(RoleDTO setUserRoleDTO) throws Exception;

    List<UserRole> getUserRole() throws Exception;

    UserRole getUserRoleByRoleName(String roleName) throws Exception;

}
