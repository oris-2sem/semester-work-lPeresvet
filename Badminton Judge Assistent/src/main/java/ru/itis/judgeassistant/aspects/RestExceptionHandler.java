package ru.itis.judgeassistant.aspects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.judgeassistant.dto.ExceptionDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(NotFoundException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(NotFoundException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .build());
    }

}
