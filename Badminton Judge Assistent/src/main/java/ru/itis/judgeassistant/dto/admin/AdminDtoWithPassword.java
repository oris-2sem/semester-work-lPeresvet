package ru.itis.judgeassistant.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.models.users.Admin;
import ru.itis.judgeassistant.models.users.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Администратор с паролем")
public class AdminDtoWithPassword {
    @Schema(description = "Логин", example = "admin_2")
    private String login;

    @Schema(description = "Пароль", example = "987654")
    private String password;

    public static Admin fromDto(AdminDtoWithPassword admin) {
        return Admin.builder()
                .login(admin.getLogin())
                .role(User.Role.ADMIN)
                .state(User.State.CONFIRMED)
                .build();
    }
}
