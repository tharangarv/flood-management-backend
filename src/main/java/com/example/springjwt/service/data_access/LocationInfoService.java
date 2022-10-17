package com.example.springjwt.service.data_access;

import com.example.springjwt.dto.LocationDTO;
import com.example.springjwt.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationInfoService {

    Location saveLocation(LocationDTO locationDTO) throws Exception;

    Location editLocation(LocationDTO locationDTO) throws Exception;

    void deleteLocation(Long locationId) throws Exception;

    Optional<Location> getLocation(Long locationId) throws Exception;

    List<Location> getAllLocations();

}
