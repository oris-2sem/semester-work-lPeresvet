package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.ScoresApi;
import ru.itis.judgeassistant.dto.score.ScoreDto;
import ru.itis.judgeassistant.services.ScoreService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scores")
public class ScoreController implements ScoresApi {
    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<ScoreDto> add() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(scoreService.create());
    }

    @PutMapping("/{id}/first_team/increment")
    public ResponseEntity<ScoreDto> incrementFirst(@PathVariable Long id) {
        return ResponseEntity.accepted().
                body(scoreService.incrementFirst(id));
    }

    @PutMapping("/{id}/first_team/decrement")
    public ResponseEntity<ScoreDto> decrementFirst(@PathVariable Long id) {
        return ResponseEntity.accepted().
                body(scoreService.decrementFirst(id));
    }

    @PutMapping("/{id}/second_team/increment")
    public ResponseEntity<ScoreDto> incrementSecond(@PathVariable Long id) {
        return ResponseEntity.accepted().
                body(scoreService.incrementSecond(id));
    }

    @PutMapping("/{id}/second_team/decrement")
    public ResponseEntity<ScoreDto> decrementSecond(@PathVariable Long id) {
        return ResponseEntity.accepted().
                body(scoreService.decrementSecond(id));
    }

    @PutMapping("/{id}/store")
    public ResponseEntity<ScoreDto> makeStored(@PathVariable Long id) {
        return ResponseEntity.accepted().
                body(scoreService.storeScore(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(scoreService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        scoreService.delete(id);
        return ResponseEntity.accepted().build();
    }

}
