package ru.itis.judgeassistant.controllers.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import ru.itis.judgeassistant.dto.SignInDto;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithId;

@Tags(value = {
        @Tag(name = "Judges")
})
public interface JudgesApi {
    @Operation(summary = "Добавление регистрация судьи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Зарегистрированный судья",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JudgeDtoWithId.class))
                    })
    })
    ResponseEntity<JudgeDtoWithId> add(SignInDto newJudge);
}
