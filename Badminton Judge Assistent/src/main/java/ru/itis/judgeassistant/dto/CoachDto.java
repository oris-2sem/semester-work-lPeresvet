package ru.itis.judgeassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Тренер")
public class CoachDto {
    @Schema(description = "Имя", example = "Пётр")
    private String name;

    @Schema(description = "Фамилия", example = "Петров")
    private String surname;

    @Schema(description = "Отчество", example = "Пётрович")
    private String patronymic;
}
