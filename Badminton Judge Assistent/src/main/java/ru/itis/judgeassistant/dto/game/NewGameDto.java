package ru.itis.judgeassistant.dto.game;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.models.Game.Status;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Новая игра")
public class NewGameDto {
    @Schema(description = "Номер подающей команды", example = "1")
    private Byte turn;

    @Schema(description = "Статус игры", example = "started")
    private Status status;

    @Schema(description = "Идентификатор корта", example = "5")
    private Long cortId;

    @Schema(description = "Идентификатор 1 команды", example = "4")
    private Long team1Id;

    @Schema(description = "Идентификатор 2 команды", example = "9")
    private Long team2Id;

    @Schema(description = "Идентификатор судьи", example = "7")
    private Long judgeId;
}
