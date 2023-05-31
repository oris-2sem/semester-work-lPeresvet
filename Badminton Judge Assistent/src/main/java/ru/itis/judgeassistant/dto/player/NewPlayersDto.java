package ru.itis.judgeassistant.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Список новых игроков")
public class NewPlayersDto {
    @Schema(description = "Новые игроки")
    private List<PlayerDto> players;
}
