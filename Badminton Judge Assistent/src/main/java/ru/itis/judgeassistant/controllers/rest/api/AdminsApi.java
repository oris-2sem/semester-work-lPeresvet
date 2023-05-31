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
import ru.itis.judgeassistant.dto.admin.AdminDto;
import ru.itis.judgeassistant.dto.admin.AdminDtoWithPassword;

@Tags(value = {
        @Tag(name = "Admins")
})
public interface AdminsApi {
    @Operation(summary = "Добавление нового админа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный админ",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdminDto.class))
                    })
    })
    ResponseEntity<AdminDto> add(AdminDtoWithPassword adminDto);

    @Operation(summary = "Обновление админа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный админ",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdminDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<AdminDto> update(Long id);

    @Operation(summary = "Удаление админа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Админ удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<?> delete(Long id);

    @Operation(summary = "Получение админа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об админе",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdminDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    ResponseEntity<AdminDto> get(Long id);

}
