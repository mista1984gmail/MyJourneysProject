package com.example.MyJourneysProject.service.impl;

import com.example.MyJourneysProject.persistence.entity.Journey;
import com.example.MyJourneysProject.persistence.repository.JourneyRepository;
import com.example.MyJourneysProject.service.JourneyService;
import com.example.MyJourneysProject.service.convertor.JourneyMapper;
import com.example.MyJourneysProject.service.dto.JourneyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository journeyRepository;
    private final JourneyMapper journeyMapper;

    @Override
    public List<JourneyDto> findAll() {
        log.info("Find all journeys");
        return journeyMapper.toListDto(journeyRepository.findAll());
    }

    @Override
    public JourneyDto findById(Long id) {
        log.info("Find Journey with id: {}", id);
        return Optional.of(getById(id)).map(journeyMapper::modelToDto).get();
    }

    @Override
    @Transactional
    public JourneyDto save(JourneyDto journeyDto) {
        log.info("Save journey: {}", journeyDto);
        return journeyMapper.modelToDto(journeyRepository.save(
                journeyMapper.dtoToModel(journeyDto)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var journey = getById(id);
        log.info("Delete journey with id: {}", id);
        journeyRepository.delete(journey);
    }
    private Journey getById(Long id) {
        return journeyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Journey with id: " + id + " not found"));
    }
}
