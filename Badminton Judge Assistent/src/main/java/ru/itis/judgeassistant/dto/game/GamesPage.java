package ru.itis.judgeassistant.dto.game;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.models.Game;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Страницы игры с количеством игр")
public class GamesPage {
    @Schema(description = "Список игр")
    private List<GameDto> games;

    @Schema(description = "Количество игр", example = "5")
    private Integer gamesNum;

}
