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
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.game.NewGameDto;

@Tags(value = {
        @Tag(name = "Games")
})
public interface GamesApi {
    @Operation(summary = "Сохдание новой игры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Созданная игра",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDto.class))
                    })
    })
    ResponseEntity<GameDto> add(NewGameDto newGameDto);

    @Operation(summary = "Обновление игры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленная игра",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<GameDto> update(Long id, NewGameDto newGameDto);


    ResponseEntity<GameDto> start(Long gameId);

    ResponseEntity<GameDto> finish(Long gameId);

    ResponseEntity<GameDto> addScore(Long gameId);

    @Operation(summary = "Удаление игры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Игра удалена"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение игры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об игре",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<GameDto> get(Long id);

    @Operation(summary = "Получение игр на корте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с играми",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GamesPage.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<GamesPage> getByCortId(Long cortId);
}
