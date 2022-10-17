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
public class UserChangePasswordDTO {

    @JsonProperty(value = "old_password", required = true)
    private String oldPassword;

    @JsonProperty(value = "new_password", required = true)
    private String newPassword;

}
