package ru.itis.judgeassistant.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.models.simpleentities.Player;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Игрок без идентификатора")
public class PlayerDto {

    @Schema(description = "Отчество", example = "Иванович")
    private String patronymic;

    @Schema(description = "Имя", example = "Иван")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    private String surname;

    @Schema(description = "Пол", example = "male")
    private Player.Sex sex;

    @Schema(description = "Возраст", example = "25")
    private Byte age;

    @Schema(description = "Количество очков", example = "1487")
    private Integer points;

    @Schema(description = "Тренер")
    private CoachDto coach;
}
