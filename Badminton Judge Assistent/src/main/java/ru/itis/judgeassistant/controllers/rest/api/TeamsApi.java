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
import ru.itis.judgeassistant.dto.player.NewPlayersDto;
import ru.itis.judgeassistant.dto.team.NewTeamDto;
import ru.itis.judgeassistant.dto.team.TeamDto;

@Tags(value = {
        @Tag(name = "Teams")
})
public interface TeamsApi {
    @Operation(summary = "Добавление новой команды")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленная команда",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamDto.class))
                    })
    })
    ResponseEntity<TeamDto> add(NewTeamDto teamDto);

    @Operation(summary = "Создание новой команды по списку игроков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленная команда",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamDto.class))
                    })
    })
    ResponseEntity<TeamDto> create(NewPlayersDto teamDto);

    @Operation(summary = "Обновление команды")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленная команда",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<TeamDto> update(Long id, NewTeamDto teamDto);

    @Operation(summary = "Удаление комадны")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Команда удалена"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение команды")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о команде",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<TeamDto> get(Long id);
}
