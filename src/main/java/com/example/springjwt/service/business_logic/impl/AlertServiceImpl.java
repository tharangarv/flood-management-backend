package com.example.springjwt.service.business_logic.impl;

import com.example.springjwt.dto.AlertDTO;
import com.example.springjwt.dto.DeviceTokenDTO;
import com.example.springjwt.entity.Alert;
import com.example.springjwt.entity.DeviceToken;
import com.example.springjwt.service.business_logic.AlertService;
import com.example.springjwt.service.data_access.AlertInfoService;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AlertServiceImpl implements AlertService {

    private final FirebaseMessaging firebaseMessaging;
    private final AlertInfoService alertInfoService;

    @Override
    public List<AlertDTO> getAlerts(boolean alertLimit) {
        try {
            List<Alert> alerts = alertInfoService.getAlerts(alertLimit);

            return alerts.stream().map(alert ->
                    AlertDTO.builder()
                            .alertId(alert.getId())
                            .alertName(alert.getAlertName())
                            .alertBody(alert.getAlertBody())
                            .timestamp(alert.getTimestamp())
                            .build()
            ).toList();

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }

    }

    @Override
    public AlertDTO getAlert(Long alertId) {
        try {
            Alert alert = alertInfoService.getAlert(alertId);

            return AlertDTO.builder()
                    .alertId(alert.getId())
                    .alertName(alert.getAlertName())
                    .alertBody(alert.getAlertBody())
                    .timestamp(alert.getTimestamp())
                    .build();

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public AlertDTO sendAlert(AlertDTO alertDTO) {

        Alert savedAlert = alertInfoService.sendAlert(alertDTO);
        List<DeviceToken> tokensList = alertInfoService.getDeviceTokens();

        List<String> tokensStringList = tokensList.stream().map(DeviceToken::getToken).toList();

        Notification notification = Notification.builder()
                .setTitle(savedAlert.getAlertName())
                .setBody(savedAlert.getAlertBody())
                .build();
//
//        Message message = Message.builder()
//                .setTopic(FirebaseConstants.TOPIC)
//                .setNotification(notification)
//                .build();

        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(tokensStringList)
                .build();

        try {
            BatchResponse returnVal = firebaseMessaging.sendMulticast(multicastMessage);
            log.info("FIREBASE MSG: {}", returnVal);

            return AlertDTO.builder()
                    .alertId(savedAlert.getId())
                    .alertName(savedAlert.getAlertName())
                    .alertBody(savedAlert.getAlertBody())
                    .timestamp(savedAlert.getTimestamp())
                    .build();

        } catch (FirebaseMessagingException e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public DeviceTokenDTO addDeviceToken(DeviceTokenDTO deviceTokenDTO) {

        try {
            DeviceToken deviceToken = alertInfoService.addDeviceToken(deviceTokenDTO);

            return DeviceTokenDTO.builder()
                    .id(deviceToken.getId())
                    .appToken(deviceToken.getToken())
                    .build();

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }

    }

}
