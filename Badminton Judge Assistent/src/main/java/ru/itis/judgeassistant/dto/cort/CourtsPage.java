package ru.itis.judgeassistant.dto.cort;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Страница кортов с количеством кортов")
public class CourtsPage {
    @Schema(description = "Список кортов")
    private List<CortDto> courts;

    @Schema(description = "Количество кортов", example = "5")
    private Integer courtsNum;
}
