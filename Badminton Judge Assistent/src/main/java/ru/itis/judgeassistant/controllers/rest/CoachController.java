package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.CoachesApi;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.services.CoachService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coaches")
public class CoachController implements CoachesApi {
    private final CoachService coachService;

    @PostMapping
    public ResponseEntity<CoachDto> add(@RequestBody CoachDto coachDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(coachService.save(coachDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoachDto> update(@PathVariable Long id,
                                           @RequestBody CoachDto coachDto) {
        return ResponseEntity.accepted()
                .body(coachService.update(id, coachDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        coachService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.findById(id));
    }

}
