package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.TeamsApi;
import ru.itis.judgeassistant.dto.player.NewPlayersDto;
import ru.itis.judgeassistant.dto.team.NewTeamDto;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.services.TeamService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController implements TeamsApi {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> add(@RequestBody NewTeamDto teamDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teamService.save(teamDto));
    }

    @PostMapping("/players")
    public ResponseEntity<TeamDto> create(@RequestBody NewPlayersDto teamDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teamService.save(teamDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> update(@PathVariable Long id,
                                            @RequestBody NewTeamDto teamDto) {
        return ResponseEntity.accepted()
                .body(teamService.update(id, teamDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findById(id));
    }
}
