package ru.itis.judgeassistant.dto.score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.models.Score;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Счёт")
public class ScoreDto {
    @Schema(description = "Идентификатор", example = "45")
    private Long id;

    @Schema(description = "Очки первой команды", example = "21")
    private Integer firstTeamPoints;

    @Schema(description = "Очки второй команды", example = "19")
    private Integer secondTeamPoints;

    @Schema(description = "Статус", example = "finished")
    private Score.Status status;
}
