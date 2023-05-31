package ru.itis.judgeassistant.controllers.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import ru.itis.judgeassistant.dto.ExceptionDto;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.score.ScoreDto;

@Tags(value = {
        @Tag(name = "Scores")
})
public interface ScoresApi {
    @Operation(summary = "Добавление нового корта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный корт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CortDto.class))
                    })
    })
    ResponseEntity<ScoreDto> add();

    @Operation(summary = "Увеличение счета первой команды на 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный счёт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> incrementFirst(Long id);

    @Operation(summary = "Уменьшение счета первой команды на 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный счёт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> decrementFirst(Long id);

    @Operation(summary = "Увеличение счета второй команды на 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный счёт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> incrementSecond(Long id);

    @Operation(summary = "Уменьшение счета второй команды на 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный счёт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> decrementSecond(Long id);

    @Operation(summary = "Изменение статуса команды на 'stored'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный счёт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> makeStored(Long id);

    @Operation(summary = "Получение счёта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о счёте",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScoreDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<ScoreDto> get(Long id);

    @Operation(summary = "Удаление счёта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "счёт удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);
}
