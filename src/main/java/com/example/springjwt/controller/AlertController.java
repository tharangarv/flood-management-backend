package com.example.springjwt.controller;


import com.example.springjwt.dto.AlertDTO;
import com.example.springjwt.dto.AlertListDTO;
import com.example.springjwt.dto.DeviceTokenDTO;
import com.example.springjwt.service.business_logic.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;

    @GetMapping()
    public ResponseEntity<AlertListDTO> getAlerts(
            @RequestParam("alert_limit") boolean alertLimit
    ) {

        List<AlertDTO> alertDTOS = alertService.getAlerts(alertLimit);

        if (alertDTOS == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AlertListDTO alertListDTO = AlertListDTO.builder()
                .alertDTOS(alertDTOS)
                .build();

        return new ResponseEntity<>(alertListDTO, HttpStatus.OK);

    }

    @GetMapping("/{alertId}")
    public ResponseEntity<AlertDTO> getAlert(
            @PathVariable("alertId") Long alertId
    ) {

        AlertDTO alert = alertService.getAlert(alertId);

        if (alert == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alert, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<AlertDTO> sendAlert(
            @RequestBody AlertDTO alertDTO
    ) {

        AlertDTO alert = alertService.sendAlert(alertDTO);

        if (alert == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(alert, HttpStatus.CREATED);
    }

    @PostMapping("/tokens")
    public ResponseEntity<DeviceTokenDTO> addDeviceToken(
            @RequestBody DeviceTokenDTO deviceTokenDTO
    ) {

        DeviceTokenDTO savedDeviceToken = alertService.addDeviceToken(deviceTokenDTO);

        if (savedDeviceToken == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(savedDeviceToken, HttpStatus.CREATED);

    }
}
