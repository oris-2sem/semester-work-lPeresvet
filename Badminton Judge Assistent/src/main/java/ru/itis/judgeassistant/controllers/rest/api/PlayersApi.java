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
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.dto.player.PlayersPage;
import ru.itis.judgeassistant.dto.team.TeamsPage;

@Tags(value = {
        @Tag(name = "Players")
})
public interface PlayersApi {
    @Operation(summary = "Добавление нового игрока")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный игрок",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayerDtoWithId.class))
                    })
    })
    ResponseEntity<PlayerDtoWithId> add(PlayerDto playerDto);

    @Operation(summary = "Обновление игрока")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный игрок",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayerDtoWithId.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<PlayerDtoWithId> update(Long id, PlayerDto playerDto);

    @Operation(summary = "Удаление игрока")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Игрок удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение игрока")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об игроке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayerDtoWithId.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<PlayerDtoWithId> get(Long id);

    @Operation(summary = "Получение списка игроков по фамилии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с игроками",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PlayersPage.class))
                    })
    })
    ResponseEntity<PlayersPage> findBySurname(String surname);

    @Operation(summary = "Получение списка команд, в которых состоит игрок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с командами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamsPage.class))
                    })
    })
    ResponseEntity<TeamsPage> findPlayerTeams(Long playerId);
}
