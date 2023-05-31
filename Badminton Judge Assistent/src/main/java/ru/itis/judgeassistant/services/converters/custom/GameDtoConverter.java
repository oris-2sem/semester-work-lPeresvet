package ru.itis.judgeassistant.services.converters.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.services.converters.mapstruct.GameMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameDtoConverter {
    private final GameMapper gameMapper;

    public List<GameDto> convert(List<Game> games) {
        return games
                .stream()
                .map(gameMapper::fromGame)
                .collect(Collectors.toList());
    }
}
