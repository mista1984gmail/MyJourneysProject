package com.example.MyJourneysProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyDto {
    private Long id;
    private String title;
    private String country;
    private String stateOrProvince;
    private String city;
    private String postalCode;
    private LocalDate dateStartOfJourney;
    private LocalDate dateEndOfJourney;
    private String description;


}
