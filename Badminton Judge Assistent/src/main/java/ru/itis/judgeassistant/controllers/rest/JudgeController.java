package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.JudgesApi;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.dto.SignInDto;
import ru.itis.judgeassistant.dto.judge.JudgeDto;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithId;
import ru.itis.judgeassistant.dto.judge.JudgeDtoWithPassword;
import ru.itis.judgeassistant.dto.judge.JudgesPageWithPasswords;
import ru.itis.judgeassistant.services.JudgeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/judges")
public class JudgeController implements JudgesApi {
    private final JudgeService judgeService;

    @PostMapping
    public ResponseEntity<JudgeDtoWithId> add(@RequestBody SignInDto newJudge) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(judgeService.save(newJudge));
    }

    @PostMapping("/group")
    public ResponseEntity<JudgesPageWithPasswords> create(@RequestParam Integer quantity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(judgeService.create(quantity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JudgeDtoWithId> update(@PathVariable Long id,
                                           @RequestBody JudgeDto coachDto) {
        return ResponseEntity.accepted()
                .body(judgeService.update(id, coachDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        judgeService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudgeDtoWithId> get(@PathVariable Long id) {
        return ResponseEntity.ok(judgeService.findById(id));
    }
    @GetMapping("/login/{login}")
    public ResponseEntity<JudgeDtoWithId> getByLogin(@PathVariable String login) {
        return ResponseEntity.ok(judgeService.findByLogin(login));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<JudgeDtoWithPassword> updatePassword(@PathVariable Long id) {
        return ResponseEntity.accepted()
                .body(judgeService.updatePassword(id));

    }

}
