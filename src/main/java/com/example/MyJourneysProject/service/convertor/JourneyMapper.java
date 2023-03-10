package com.example.MyJourneysProject.service.convertor;

import com.example.MyJourneysProject.persistence.entity.Journey;
import com.example.MyJourneysProject.service.dto.JourneyDto;
import com.example.MyJourneysProject.web.dto.request.JourneyRequest;
import com.example.MyJourneysProject.web.dto.response.JourneyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JourneyMapper {
    Journey dtoToModel(JourneyDto journeyDto);

    JourneyDto modelToDto(Journey journey);

    List<JourneyDto> toListDto(List<Journey> journeys);

    JourneyDto requestToDto(JourneyRequest journeyRequest);

    JourneyResponse dtoToResponse(JourneyDto journeyDto);

    List<JourneyResponse> toListResponse(
            List<JourneyDto> journeyDtos);
}
