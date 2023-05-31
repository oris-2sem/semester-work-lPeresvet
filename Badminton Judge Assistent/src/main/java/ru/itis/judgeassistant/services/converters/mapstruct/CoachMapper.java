package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.models.simpleentities.Coach;

@Mapper(componentModel = "spring")
public interface CoachMapper {
    CoachDto fromCoach(Coach coach);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "players", ignore = true)
    Coach fromDto(CoachDto coachDto);
}
