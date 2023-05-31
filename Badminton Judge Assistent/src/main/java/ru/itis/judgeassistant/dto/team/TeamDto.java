package ru.itis.judgeassistant.dto.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.dto.player.PlayerDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDto {
    private Long id;
    private List<PlayerDto> players;
}
