package com.booking.propertyservice.controller;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.propertyservice.service.propertyservice.PropertyService;
import com.booking.propertyservice.dto.response.PropertyDetailsDto;
import com.booking.propertyservice.dto.response.PropertyDto;
import com.booking.propertyservice.dto.request.SearchCriteria;
import com.booking.propertyservice.dto.response.PropertyDtoPage;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Operation(summary = "Search properties based on country, reservations, and guest number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Properties found", response = PropertyDtoPage.class),
            @ApiResponse(code = 404, message = "Properties don't found ", response = EntityNotFoundException.class)
    })
    @GetMapping("/search")
    public ResponseEntity<PropertyDtoPage> searchProperties(@Valid SearchCriteria searchCriteria) {
        PropertyDtoPage propertyDtoPage = propertyService.searchProperties(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(propertyDtoPage);
    }

    @Operation(summary = "Get an property by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Property found", response = PropertyDetailsDto.class),
            @ApiResponse(code = 404, message = "Property not found", response = EntityNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDetailsDto> getPropertyById(@PathVariable String id) {
        PropertyDetailsDto propertyDto = propertyService.getPropertyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(propertyDto);
    }

    @Operation(summary = "Create Property")
    @ApiResponse(code = 201, message = "Property created", response = PropertyDetailsDto.class)
    @PostMapping
    public ResponseEntity<PropertyDetailsDto> createProperty(@NotNull @RequestPart("file") MultipartFile[] files,
                                                             @NotEmpty @RequestPart("property") String property) {
        PropertyDetailsDto propertyDetailsDto = propertyService.createProperty(files, property);
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyDetailsDto);
    }

    @Operation(summary = "Delete Property")
    @ApiResponse(code = 201, message = "Property deleted", response = PropertyDetailsDto.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable String id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted property with id:" + id);
    }

    @Operation(summary = "Get properties based on user's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Properties found based on user's id", response = PropertyDetailsDto.class),
            @ApiResponse(code = 404, message = "Properties don't found based on user's id", response = EntityNotFoundException.class)
    })
    @GetMapping("/owner/{id}")
    public ResponseEntity<Set<PropertyDto>> getPropertyByOwnerId(@PathVariable String id) {
        Set<PropertyDto> properties = propertyService.getPropertiesByOwnerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(properties);
    }
}
