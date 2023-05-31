package ru.itis.judgeassistant.services.converters.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.models.simpleentities.Player;
import ru.itis.judgeassistant.services.converters.mapstruct.PlayerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlayerDtoConverter {
    private final PlayerMapper playerMapper;

    public List<PlayerDtoWithId> convert(List<Player> players) {
        return players.stream()
                .map(playerMapper::fromPlayerWithId)
                .collect(Collectors.toList());
    }
}
