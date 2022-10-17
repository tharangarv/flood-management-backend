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
public class DeviceTokenDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty(value = "app_token", required = true)
    private String appToken;

}
