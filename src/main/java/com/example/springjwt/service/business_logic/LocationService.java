package com.example.springjwt.service.business_logic;

import com.example.springjwt.dto.LocationDTO;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    LocationDTO saveLocation(LocationDTO locationDTO);

    LocationDTO editLocation(LocationDTO locationDTO);

    boolean deleteLocation(Long locationId);

    Optional<LocationDTO> getLocation(Long locationId);

    List<LocationDTO> getAllLocations();

}
