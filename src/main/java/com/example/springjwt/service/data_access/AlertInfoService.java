package com.example.springjwt.service.data_access;

import com.example.springjwt.dto.AlertDTO;
import com.example.springjwt.dto.DeviceTokenDTO;
import com.example.springjwt.entity.Alert;
import com.example.springjwt.entity.DeviceToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlertInfoService {

    List<Alert> getAlerts(boolean alertLimit) throws Exception;

    Alert getAlert(Long alertId) throws Exception;

    Alert sendAlert(AlertDTO alertDTO);

    DeviceToken addDeviceToken(DeviceTokenDTO deviceTokenDTO) throws Exception;

    List<DeviceToken> getDeviceTokens();

}
