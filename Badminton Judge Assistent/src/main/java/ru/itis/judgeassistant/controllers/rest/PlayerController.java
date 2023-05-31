package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.PlayersApi;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.dto.player.PlayersPage;
import ru.itis.judgeassistant.dto.team.TeamsPage;
import ru.itis.judgeassistant.services.PlayerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController implements PlayersApi {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDtoWithId> add(@RequestBody PlayerDto playerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.save(playerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDtoWithId> update(@PathVariable Long id,
                                           @RequestBody PlayerDto playerDto) {
        return ResponseEntity.accepted()
                .body(playerService.update(id, playerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDtoWithId> get(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PlayersPage> findBySurname(@RequestParam(name = "surname") String surname) {
        return ResponseEntity.ok(playerService.findPlayersBySurnameLike(surname));
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<TeamsPage> findPlayerTeams(@PathVariable(name = "id") Long playerId) {
        return ResponseEntity.ok(playerService.findTeams(playerId));
    }
    @GetMapping("/{id}/games")
    public ResponseEntity<GamesPage> getGames(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getGamesByPlayerId(id));
    }

    @GetMapping("/{id}/teammates")
    public ResponseEntity<PlayersPage> getTeammates(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findPlayerTeammates(id));
    }

}
