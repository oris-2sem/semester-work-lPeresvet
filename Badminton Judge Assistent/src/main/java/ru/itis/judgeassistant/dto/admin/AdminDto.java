package ru.itis.judgeassistant.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.models.users.Admin;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Администратор")
public class AdminDto {
    @Schema(description = "Идентификатор администратора", example = "5")
    private Long id;

    @Schema(description = "Имя пользователя", example = "admin_8")
    private String login;

    public static AdminDto from(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .login(admin.getLogin())
                .build();
    }

}
