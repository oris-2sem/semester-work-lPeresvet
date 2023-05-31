package ru.itis.judgeassistant.dto.game;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.dto.team.TeamDto;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Результаты игры")
public class PlayersScoresDto {
    @Schema(description = "Первая команда")
    private TeamDto firstTeam;

    @Schema(description = "Вторая команда")
    private TeamDto secondTeam;

    @Schema(description = "Номер подающей команды", example = "2")
    private Byte turn;

    @Schema(description = "Список счётов по геймам")
    private List<ScoreDto> scores;
}
