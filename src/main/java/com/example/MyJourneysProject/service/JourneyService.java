package com.example.MyJourneysProject.service;

import com.example.MyJourneysProject.service.dto.JourneyDto;

import java.util.List;

public interface JourneyService {
    List<JourneyDto> findAll ();
    JourneyDto findById( Long id);
    JourneyDto save (JourneyDto journey);
    void deleteById (Long id);

}
