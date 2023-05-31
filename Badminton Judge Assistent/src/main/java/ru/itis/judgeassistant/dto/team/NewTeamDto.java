package ru.itis.judgeassistant.dto.team;

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
@Schema(description = "Список идентификаторов игроков")
public class NewTeamDto {
    @Schema(description = "Идентификаторы игроков")
    private List<Long> players;
}
