package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.dto.player.PlayersPage;
import ru.itis.judgeassistant.dto.team.TeamsPage;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.simpleentities.Player;
import ru.itis.judgeassistant.repositories.PlayerRepository;

public interface PlayerService {
    PlayerDtoWithId save(PlayerDto newPlayer);
    PlayerDtoWithId findById(Long id);
    PlayerDtoWithId update(Long playerId, PlayerDto updatePlayer);
    void delete(Long playerId);

    PlayersPage findPlayersBySurnameLike(String nameStart);

    PlayersPage findPlayerTeammates(Long playerId);

    TeamsPage findTeams(Long playerId);
    GamesPage getGamesByPlayerId(Long playerId);

    static Player getOrElseThrow(Long playerId, PlayerRepository playerRepository) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new NotFoundException("No player with id: <" + playerId + "> found"));
    }
}
