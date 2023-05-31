package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.admin.AdminDto;
import ru.itis.judgeassistant.dto.admin.AdminDtoWithPassword;
import ru.itis.judgeassistant.models.users.Admin;
import ru.itis.judgeassistant.models.users.User;
import ru.itis.judgeassistant.repositories.AdminRepository;
import ru.itis.judgeassistant.services.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminDto getById(Long id) {
        Admin admin = AdminService.getOrElseThrow(id, adminRepository);
        return AdminDto.from(admin);
    }

    @Override
    public AdminDto ban(Long id) {
        Admin admin = AdminService.getOrElseThrow(id, adminRepository);
        admin.setState(User.State.BANNED);
        adminRepository.save(admin);
        return AdminDto.from(admin);
    }

    @Override
    public AdminDto save(AdminDtoWithPassword newAdminDto) {
        Admin admin = AdminDtoWithPassword.fromDto(newAdminDto);
        admin.setHashPassword(passwordEncoder.encode(newAdminDto.getPassword()));

        adminRepository.save(admin);
        return AdminDto.from(admin);
    }

    @Override
    public void delete(Long id) {
        Admin admin = AdminService.getOrElseThrow(id, adminRepository);

        adminRepository.delete(admin);
    }
}
