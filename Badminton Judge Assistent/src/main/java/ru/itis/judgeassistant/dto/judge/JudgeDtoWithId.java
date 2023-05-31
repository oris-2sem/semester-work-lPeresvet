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
@Schema(description = "Судья с идентификатором")
public class JudgeDtoWithId {
    @Schema(description = "Идентификатор", example = "46")
    private Long id;

    @Schema(description = "Имя", example = "Виктор")
    private String name;

    @Schema(description = "Фамилия", example = "Кожедуб")
    private String surname;

    @Schema(description = "Имя пользователя", example = "judge_147")
    private String login;
}
