package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.models.simpleentities.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDto fromPlayer(Player player);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teams", ignore = true)
    Player fromDto(PlayerDto playerDto);

    PlayerDtoWithId fromPlayerWithId(Player player);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "teams", ignore = true)
    Player fromDto(PlayerDtoWithId playerDto);
}
