package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.GamesApi;
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.game.NewGameDto;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.services.GameService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController implements GamesApi{
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameDto> add(@RequestBody NewGameDto newGameDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameService.create(newGameDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDto> update(@PathVariable Long id,
                                          @RequestBody NewGameDto newGameDto) {
        return ResponseEntity.accepted()
                .body(gameService.update(id, newGameDto));
    }

    @PutMapping("/{id}/started")
    public ResponseEntity<GameDto> start(@PathVariable(name = "id") Long gameId) {
        return ResponseEntity.accepted()
                .body(gameService.setStatus(gameId, Game.Status.STARTED));
    }

    @PutMapping("/{id}/finished")
    public ResponseEntity<GameDto> finish(@PathVariable(name = "id") Long gameId) {
        return ResponseEntity.accepted()
                .body(gameService.setStatus(gameId, Game.Status.FINISHED));
    }

    @PutMapping("/{id}/new_score")
    public ResponseEntity<GameDto> addScore(@PathVariable(name = "id") Long gameId) {
        return ResponseEntity.accepted()
                .body(gameService.addNewScore(gameId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findById(id));
    }


    @GetMapping("/cort/{id}")
    public ResponseEntity<GamesPage> getByCortId(@PathVariable(name = "id") Long cortId) {
        return ResponseEntity.ok(gameService.getGameByCortId(cortId));
    }

}
