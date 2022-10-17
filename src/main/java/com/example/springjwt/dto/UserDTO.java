package com.example.springjwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty(value = "email", required = true)
    private String email;
    @JsonProperty(value = "password", required = true)
    private String password;
    @JsonProperty("role")
    private String role;

}
