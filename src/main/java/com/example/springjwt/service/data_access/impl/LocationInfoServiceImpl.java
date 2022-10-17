package com.example.springjwt.service.data_access.impl;

import com.example.springjwt.dto.LocationDTO;
import com.example.springjwt.entity.Location;
import com.example.springjwt.repository.LocationRepo;
import com.example.springjwt.service.data_access.LocationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationInfoServiceImpl implements LocationInfoService {

    private final LocationRepo locationRepo;

    @Override
    public Location saveLocation(LocationDTO locationDTO) throws Exception {

        if (locationDTO.getLatitude().isBlank()) throw new Exception("Latitude is empty");
        if (locationDTO.getLongitude().isBlank()) throw new Exception("Longitude is empty");
        if (locationDTO.getName().isBlank()) throw new Exception("Name is empty");

        Location location = Location.builder()
                .locationName(locationDTO.getName())
                .longitude(locationDTO.getLongitude())
                .latitude(locationDTO.getLatitude())
                .build();

        return locationRepo.save(location);

    }

    @Override
    public Location editLocation(LocationDTO locationDTO) throws Exception {

        if (locationDTO.getLatitude().isBlank()) throw new Exception("Latitude is empty");
        if (locationDTO.getLongitude().isBlank()) throw new Exception("Longitude is empty");
        if (locationDTO.getName().isBlank()) throw new Exception("Name is empty");
        if (locationDTO.getId() == null) throw new Exception("Location Id is empty");

        Location location = locationRepo.findById(locationDTO.getId()).orElse(null);

        if (location == null) throw new Exception("Location not found from given Id");

        location.setLocationName(locationDTO.getName());
        location.setLongitude(locationDTO.getLongitude());
        location.setLatitude(locationDTO.getLatitude());

        return locationRepo.save(location);
    }

    @Override
    public void deleteLocation(Long locationId) throws Exception {

        if (locationId == null) throw new Exception("Location Id is empty");

        locationRepo.deleteById(locationId);

    }

    @Override
    public Optional<Location> getLocation(Long locationId) throws Exception {

        if (locationId == null) throw new Exception("Location Id is empty");

        return locationRepo.findById(locationId);

    }

    @Override
    public List<Location> getAllLocations() {

        return locationRepo.findAll();

    }
}
