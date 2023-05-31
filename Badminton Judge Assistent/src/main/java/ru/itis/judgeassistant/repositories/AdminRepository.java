package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.users.Admin;
import ru.itis.judgeassistant.models.users.Judge;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByLogin(String username);
}
