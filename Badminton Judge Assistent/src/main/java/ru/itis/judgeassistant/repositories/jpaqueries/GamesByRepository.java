package ru.itis.judgeassistant.repositories.jpaqueries;

import ru.itis.judgeassistant.models.Game;

import java.util.List;

public interface GamesByRepository {
    List<Game> getGamesByUserId(Long userId);
}
