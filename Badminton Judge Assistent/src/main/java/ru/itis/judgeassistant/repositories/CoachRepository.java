package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.simpleentities.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
