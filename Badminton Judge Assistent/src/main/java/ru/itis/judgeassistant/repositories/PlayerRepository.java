package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.models.simpleentities.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findPlayersBySurnameStartsWithIgnoreCase(String surname);
}
