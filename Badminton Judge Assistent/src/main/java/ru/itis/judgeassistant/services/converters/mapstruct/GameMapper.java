package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.NewGameDto;
import ru.itis.judgeassistant.models.Game;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    List<GameDto> fromGames(List<Game> games);
    GameDto fromGame(Game game);

//    @Mapping(target = "id", ignore = true)
//    Game fromDto(GameDto gameDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "scores", ignore = true)
    @Mapping(target = "judge", ignore = true)
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "cort", ignore = true)
    Game fromNewDto(NewGameDto newGameDto);
}
