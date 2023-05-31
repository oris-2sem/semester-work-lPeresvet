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
@Schema(description = "Новый корт")
public class NewCortDto {
    @Schema(description = "Номер корта", example = "5")
    private Integer number;
}
