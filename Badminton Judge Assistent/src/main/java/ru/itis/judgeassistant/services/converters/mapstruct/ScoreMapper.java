package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.models.Score;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
    ScoreDto fromScore(Score score);

    @Mapping(target = "game", ignore = true)
    Score fromDto(ScoreDto scoreDto);
}
