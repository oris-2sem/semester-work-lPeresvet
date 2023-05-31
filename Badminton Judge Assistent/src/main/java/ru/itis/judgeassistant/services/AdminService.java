package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.admin.AdminDto;
import ru.itis.judgeassistant.dto.admin.AdminDtoWithPassword;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Cort;
import ru.itis.judgeassistant.models.users.Admin;
import ru.itis.judgeassistant.repositories.AdminRepository;
import ru.itis.judgeassistant.repositories.CortRepository;

public interface AdminService {
    AdminDto getById(Long id);
    AdminDto ban(Long id);
    AdminDto save(AdminDtoWithPassword newAdminDto);
    void delete(Long id);

    static Admin getOrElseThrow(Long adminId, AdminRepository adminRepository) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new NotFoundException("No admin with id: <" + adminId + "> found"));
    }
}
