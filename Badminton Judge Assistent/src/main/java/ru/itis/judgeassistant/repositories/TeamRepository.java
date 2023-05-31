package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
