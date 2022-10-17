package com.example.springjwt.service.data_access.impl;

import com.example.springjwt.dto.AlertDTO;
import com.example.springjwt.dto.DeviceTokenDTO;
import com.example.springjwt.entity.Alert;
import com.example.springjwt.entity.DeviceToken;
import com.example.springjwt.repository.AlertRepo;
import com.example.springjwt.repository.DeviceTokenRepo;
import com.example.springjwt.service.data_access.AlertInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlertInfoServiceImpl implements AlertInfoService {

    private final AlertRepo alertRepo;
    private final DeviceTokenRepo deviceTokenRepo;

    @Override
    public List<Alert> getAlerts(boolean alertLimit) throws Exception {

        List<Alert> alerts;

        if (alertLimit) {
            alerts = alertRepo.findTop10ByOrderByIdDesc();
        } else {
            alerts = alertRepo.findByOrderByIdDesc();
        }


        if (alerts.isEmpty()) throw new Exception("Alert not found");

        return alerts;

    }

    @Override
    public Alert getAlert(Long alertId) throws Exception {
        Optional<Alert> alert = alertRepo.findById(alertId);

        if (alert.isEmpty()) throw new Exception("Alert not found from given Id");

        return alert.get();

    }

    @Override
    public Alert sendAlert(AlertDTO alertDTO) {

        Alert alert = Alert.builder()
                .alertName(alertDTO.getAlertName())
                .alertBody(alertDTO.getAlertBody())
                .build();

        return alertRepo.save(alert);

    }

    @Override
    public DeviceToken addDeviceToken(DeviceTokenDTO deviceTokenDTO) throws Exception {

        if (deviceTokenDTO.getAppToken().isEmpty()) throw new Exception("Token not found");

        DeviceToken deviceToken = DeviceToken.builder()
                .token(deviceTokenDTO.getAppToken())
                .build();

        return deviceTokenRepo.save(deviceToken);
    }

    @Override
    public List<DeviceToken> getDeviceTokens() {

        return deviceTokenRepo.findAll();

    }
}
