package com.example.springjwt.service.business_logic;

import com.example.springjwt.dto.RoleDTO;
import com.example.springjwt.dto.UserRoleListDTO;

public interface RoleService {

    RoleDTO saveRole(RoleDTO roleDTO);

    UserRoleListDTO getRole();

}
