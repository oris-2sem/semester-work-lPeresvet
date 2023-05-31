package ru.itis.judgeassistant.dto.judge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Форма создания нового судьи")
public class JudgeSignUpDto {
    @Schema(description = "Имя", example = "Виктор")
    private String name;

    @Schema(description = "Фамилия", example = "Кожедуб")
    private String surname;
}
