package com.example.springjwt.service.business_logic.impl;

import com.example.springjwt.dto.LocationDTO;
import com.example.springjwt.entity.Location;
import com.example.springjwt.service.business_logic.LocationService;
import com.example.springjwt.service.data_access.LocationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationInfoService locationInfoService;

    @Override
    public LocationDTO saveLocation(LocationDTO locationDTO) {
        try {
            Location location = locationInfoService.saveLocation(locationDTO);
            LocationDTO savedLocation = LocationDTO.builder()
                    .name(location.getLocationName())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();

            log.info("Location saved : {}", savedLocation);
            return savedLocation;

        } catch (Exception e) {
            log.error("Error : Location not saved : {}", e.getMessage());
            return null;
        }

    }

    @Override
    public LocationDTO editLocation(LocationDTO locationDTO) {
        try {
            Location location = locationInfoService.editLocation(locationDTO);
            LocationDTO updatedLocation = LocationDTO.builder()
                    .name(location.getLocationName())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();

            log.info("Location updated : {}", updatedLocation);
            return updatedLocation;

        } catch (Exception e) {
            log.error("Error : Location not updated");
            return null;
        }
    }

    @Override
    public boolean deleteLocation(Long locationId) {
        try {
            locationInfoService.deleteLocation(locationId);
            log.info("Location Deleted, LocationId : {}", locationId);
            return true;
        } catch (Exception e) {
            log.error("Error : Location not deleted : {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<LocationDTO> getLocation(Long locationId) {

        try {
            Optional<Location> location = locationInfoService.getLocation(locationId);

            if (location.isEmpty()) {
                log.info("Location is not found from give LocationId : {}", locationId);
                return Optional.empty();
            }

            Optional<LocationDTO> convertedLocation = Optional.of(
                    LocationDTO.builder()
                            .name(location.get().getLocationName())
                            .longitude(location.get().getLongitude())
                            .latitude(location.get().getLatitude())
                            .build()
            );

            log.info("Location was found : {}", convertedLocation);
            return convertedLocation;

        } catch (Exception e) {
            log.error("Error : Location not found : {}", e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public List<LocationDTO> getAllLocations() {

        List<Location> locations = locationInfoService.getAllLocations();

        if (locations.isEmpty()) {
            log.info("No Location found");
            return null;
        }

        List<LocationDTO> convertedLocations = locations.stream().map(location -> LocationDTO.builder()
                .id(location.getId())
                .name(location.getLocationName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build()
        ).collect(Collectors.toList());

        log.info("Locations lists : {}", convertedLocations);

        return convertedLocations;
    }
}
