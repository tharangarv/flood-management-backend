package com.example.springjwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertDTO {

    @JsonProperty("id")
    private Long alertId;

    @JsonProperty(value = "alert_name", required = true)
    private String alertName;

    @JsonProperty(value = "alert_body", required = true)
    private String alertBody;

    @JsonProperty("timestamp")
    private Timestamp timestamp;

}
