package ru.itis.judgeassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Исключение")
public class ExceptionDto {
    @Schema(description = "Сообщение об ошибке", example = "Польхователь не найден")
    private String message;

    @Schema(description = "Код ошибки", example = "404")
    private int status;
}
