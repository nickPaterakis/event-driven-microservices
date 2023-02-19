package com.booking.propertyservice.controller;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.dto.response.CountryDto;
import com.booking.propertyservice.service.countryservice.CountryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "Get all countries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Countries found", response = List.class),
            @ApiResponse(code = 404, message = "Countries not found", response = EntityNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<CountryDto> countries = countryService.getCountries();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }
}
