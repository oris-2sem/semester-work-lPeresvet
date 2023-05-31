package ru.itis.judgeassistant.controllers.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.dto.ExceptionDto;

@Tags(value = {
        @Tag(name = "Coaches")
})
public interface CoachesApi {
    @Operation(summary = "Добавление нового судьи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный судья",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CoachDto.class))
                    })
    })
    ResponseEntity<CoachDto> add(CoachDto coachDto);

    @Operation(summary = "Обновление судьи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный судья",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CoachDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<CoachDto> update(Long id, CoachDto coachDto);

    @Operation(summary = "Удаление судьи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Судья удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение судьи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о судье",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CoachDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<CoachDto> get(Long id);
}
