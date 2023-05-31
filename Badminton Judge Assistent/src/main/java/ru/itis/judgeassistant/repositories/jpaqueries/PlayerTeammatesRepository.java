package ru.itis.judgeassistant.repositories.jpaqueries;

import ru.itis.judgeassistant.models.simpleentities.Player;

import java.util.List;

public interface PlayerTeammatesRepository {
    List<Player> getTeammates(Long playerId);
}
