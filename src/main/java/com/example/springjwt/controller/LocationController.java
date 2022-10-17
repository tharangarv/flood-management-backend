package com.example.springjwt.controller;

import com.example.springjwt.dto.LocationDTO;
import com.example.springjwt.dto.LocationListDTO;
import com.example.springjwt.service.business_logic.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping()
    public ResponseEntity<LocationDTO> saveLocation(
            @RequestBody LocationDTO locationDTO
    ) {
        LocationDTO savedLocation = locationService.saveLocation(locationDTO);
        if (savedLocation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<LocationDTO> editLocation(
            @RequestBody LocationDTO locationDTO
    ) {
        LocationDTO updatedLocation = locationService.editLocation(locationDTO);
        if (updatedLocation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<String> deleteLocation(
            @PathVariable("locationId") long locationId
    ) {
        boolean isDeleted = locationService.deleteLocation(locationId);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> getLocation(
            @PathVariable("locationId") long locationId
    ) {
        Optional<LocationDTO> locations = locationService.getLocation(locationId);

        if (locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locations.get(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<LocationListDTO> getLocations() {

        List<LocationDTO> locations = locationService.getAllLocations();

        if (locations == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LocationListDTO locationListDTO = LocationListDTO.builder()
                .locationDTOS(locations)
                .build();

        return new ResponseEntity<>(locationListDTO, HttpStatus.OK);

    }
}
