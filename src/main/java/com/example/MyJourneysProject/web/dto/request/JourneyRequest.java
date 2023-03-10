package com.example.MyJourneysProject.web.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journey")
public class JourneyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
