package ru.itis.judgeassistant.dto.game;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.judge.JudgeDto;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.models.Game;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Игра")
public class GameDto {
    @Schema(description = "Идентификатор игры", example = "5")
    private Long id;

    @Schema(description = "Время начала игры")
    private Date time;

    @Schema(description = "Номер подающей команды", example = "2")
    private Byte turn;

    @Schema(description = "Статус", example = "prepared")
    private Game.Status status;

    @Schema(description = "Корт")
    private CortDto cort;
    @Schema(description = "Список команд")
    private List<TeamDto> teams;

    @Schema(description = "Список счётов")
    private List<ScoreDto> scores;

    @Schema(description = "Судья")
    private JudgeDto judge;

}
