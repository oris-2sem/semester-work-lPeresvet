package ru.itis.judgeassistant.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Страница игроков и колчество игроков")
public class PlayersPage {
    @Schema(description = "Список игроков")
    private List<PlayerDtoWithId> players;

    @Schema(description = "Количество игроков", example = "15")
    private Integer playerNum;
}
