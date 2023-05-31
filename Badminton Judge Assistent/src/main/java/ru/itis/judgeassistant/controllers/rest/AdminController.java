package ru.itis.judgeassistant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.controllers.rest.api.AdminsApi;
import ru.itis.judgeassistant.dto.admin.AdminDto;
import ru.itis.judgeassistant.dto.admin.AdminDtoWithPassword;
import ru.itis.judgeassistant.services.AdminService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admins")
public class AdminController implements AdminsApi {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> add(@RequestBody AdminDtoWithPassword adminDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.save(adminDto));
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<AdminDto> update(@PathVariable Long id) {
        return ResponseEntity.accepted()
                .body(adminService.ban(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adminService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getById(id));
    }

}
