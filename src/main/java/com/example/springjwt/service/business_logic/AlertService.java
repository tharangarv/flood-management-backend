package com.example.springjwt.service.business_logic;

import com.example.springjwt.dto.AlertDTO;
import com.example.springjwt.dto.DeviceTokenDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlertService {

    List<AlertDTO> getAlerts(boolean alertLimit);

    AlertDTO getAlert(Long alertId);

    AlertDTO sendAlert(AlertDTO alertDTO);

    DeviceTokenDTO addDeviceToken(DeviceTokenDTO deviceTokenDTO);

}
