package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.CourtsApi;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.cort.CourtsPage;
import ru.itis.judgeassistant.dto.cort.NewCortDto;
import ru.itis.judgeassistant.services.CortService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courts")
public class CortController implements CourtsApi {
    private final CortService cortService;

    @PostMapping
    public ResponseEntity<CortDto> add(@RequestBody NewCortDto newCortDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cortService.save(newCortDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CortDto> update(@PathVariable Long id,
                                          @RequestBody CortDto cortDto) {
        return ResponseEntity.accepted()
                .body(cortService.update(id, cortDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cortService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<CourtsPage> findAll() {
        return ResponseEntity.ok(cortService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CortDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(cortService.findById(id));
    }
}
