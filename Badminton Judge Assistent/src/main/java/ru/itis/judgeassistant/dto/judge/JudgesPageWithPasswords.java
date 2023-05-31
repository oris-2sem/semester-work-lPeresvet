package ru.itis.judgeassistant.dto.judge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Страница судей и колчество судей")
public class JudgesPageWithPasswords {
    @Schema(description = "Список судей")
    private List<JudgeDtoWithPassword> judges;

    @Schema(description = "Количество судей", example = "15")
    private Integer judgesNum;
}
