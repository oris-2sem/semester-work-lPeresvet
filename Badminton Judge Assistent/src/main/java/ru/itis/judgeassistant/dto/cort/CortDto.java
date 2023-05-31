package ru.itis.judgeassistant.dto.cort;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Корт")
public class CortDto {
    @Schema(description = "Идентификатор корта", example = "5")
    private Long id;

    @Schema(description = "Номер корта", example = "8")
    private Integer number;
}
