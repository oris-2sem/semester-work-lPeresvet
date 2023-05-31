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
import ru.itis.judgeassistant.dto.cort.CourtsPage;
import ru.itis.judgeassistant.dto.cort.NewCortDto;

@Tags(value = {
        @Tag(name = "Courts")
})
public interface CourtsApi {
    @Operation(summary = "Добавление нового корта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный корт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CortDto.class))
                    })
    })
    ResponseEntity<CortDto> add(NewCortDto newCortDto);

    @Operation(summary = "Обновление корта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный корт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CortDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<CortDto> update(Long id, CortDto cortDto);

    @Operation(summary = "Удаление корта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Корт удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение списка кортов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с кортами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CourtsPage.class))
                    })
    })
    ResponseEntity<CourtsPage> findAll();

    @Operation(summary = "Получение корта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о корте",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CortDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<CortDto> get(Long id);
}
