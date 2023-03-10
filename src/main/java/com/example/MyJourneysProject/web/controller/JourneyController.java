package com.example.MyJourneysProject.web.controller;

import com.example.MyJourneysProject.persistence.entity.Journey;
import com.example.MyJourneysProject.service.JourneyService;
import com.example.MyJourneysProject.service.convertor.JourneyMapper;
import com.example.MyJourneysProject.web.dto.request.JourneyRequest;
import com.example.MyJourneysProject.web.dto.response.JourneyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class JourneyController {
    private final JourneyService journeyService;
    private final JourneyMapper journeyMapper;

    @Operation(summary = "Find all journeys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find journeys", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/journeys")
    List<JourneyResponse> journeys() {
        log.info("Find all journeys");
        return journeyMapper.toListResponse(journeyService.findAll());
    }

    @Operation(summary = "Get journey by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the journey", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/journey/{id}")
    @ResponseStatus(HttpStatus.OK)
    JourneyResponse getJourney(@PathVariable Long id) {
        log.info("Request to get journey by id: {}", id);
        JourneyResponse journey = journeyMapper.dtoToResponse(journeyService.findById(id));
        return journey;
    }

    @Operation(summary = "Save journey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save journey", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/journey")
    ResponseEntity<JourneyResponse> createJourney(@Valid @RequestBody JourneyRequest journey) throws URISyntaxException {
        log.info("Request to create journey: {}", journey);
        JourneyResponse result = journeyMapper.dtoToResponse(journeyService.save(journeyMapper.requestToDto(journey)));
        return ResponseEntity.created(new URI("/api/journey/" + result.getId()))
                .body(result);
    }

    @Operation(summary = "Update journey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update journey", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping("/journey/{id}")
    ResponseEntity<JourneyResponse> updateJourney(@Valid @RequestBody JourneyRequest journey) {
        log.info("Request to update journey: {}", journey);
        JourneyResponse result = journeyMapper.dtoToResponse(journeyService.save(journeyMapper.requestToDto(journey)));
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Delete journey by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the journey"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping("/journey/{id}")
    public ResponseEntity<?> deleteJourney(@PathVariable Long id) {
        log.info("Request to delete journey: {}", id);
        journeyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
