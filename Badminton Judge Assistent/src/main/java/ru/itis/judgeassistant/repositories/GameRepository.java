package ru.itis.judgeassistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.judgeassistant.models.Game;

import java.util.Date;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> getGamesByTimeBetween(Date timeStart, Date timeFinish);
    List<Game> getGameByCortId(Long cortId);
    Game getFirstGameByCortIdAndStatusOrderByTimeDesc(Long cortId, Game.Status status);
}
