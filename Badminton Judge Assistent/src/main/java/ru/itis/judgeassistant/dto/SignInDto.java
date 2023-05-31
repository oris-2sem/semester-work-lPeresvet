package ru.itis.judgeassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Форма для входа")
public class SignInDto {
    @Schema(description = "Логин", example = "admin")
    @NotNull
    private String login;

    @Schema(description = "Пароль", example = "12346")
    @NotNull
    private String password;
}
