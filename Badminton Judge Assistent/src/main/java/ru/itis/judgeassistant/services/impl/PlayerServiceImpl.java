package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.game.GameDto;
import ru.itis.judgeassistant.dto.game.GamesPage;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.dto.player.PlayerDto;
import ru.itis.judgeassistant.dto.player.PlayerDtoWithId;
import ru.itis.judgeassistant.dto.player.PlayersPage;
import ru.itis.judgeassistant.dto.team.TeamsPage;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.models.simpleentities.Player;
import ru.itis.judgeassistant.repositories.PlayerRepository;
import ru.itis.judgeassistant.repositories.jpaqueries.GamesByRepository;
import ru.itis.judgeassistant.repositories.jpaqueries.PlayerTeammatesRepository;
import ru.itis.judgeassistant.services.PlayerService;
import ru.itis.judgeassistant.services.converters.custom.GameDtoConverter;
import ru.itis.judgeassistant.services.converters.custom.PlayerDtoConverter;
import ru.itis.judgeassistant.services.converters.mapstruct.PlayerMapper;
import ru.itis.judgeassistant.services.converters.mapstruct.TeamMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper playerMapper;
    private final TeamMapper teamMapper;
    private final GameDtoConverter gameDtoConverter;
    private final PlayerDtoConverter playerDtoConverter;
    private final PlayerTeammatesRepository playerTeammatesRepository;
    private final GamesByRepository gamesByRepository;

    @Override
    public PlayerDtoWithId save(PlayerDto newPlayer) {
        Player player = playerMapper.fromDto(newPlayer);

        repository.save(player);

        return playerMapper.fromPlayerWithId(player);
    }

    @Override
    public PlayerDtoWithId findById(Long id) {
        Player player = getOrElseThrow(id);
        return playerMapper.fromPlayerWithId(player);
    }

    @Override
    public PlayerDtoWithId update(Long playerId, PlayerDto updatePlayer) {
        Player player = getOrElseThrow(playerId);

        player.setName(updatePlayer.getName());
        player.setSurname(updatePlayer.getSurname());
        player.setPatronymic(updatePlayer.getPatronymic());
        player.setAge(updatePlayer.getAge());
        player.setPoints(updatePlayer.getPoints());
        player.setSex(updatePlayer.getSex());

        repository.save(player);
        return playerMapper.fromPlayerWithId(player);
    }

    @Override
    public void delete(Long playerId) {
        Player player = getOrElseThrow(playerId);

        repository.delete(player);
    }

    @Override
    public PlayersPage findPlayersBySurnameLike(String surnameLike) {
        if (surnameLike.equals("")) {
            return PlayersPage
                    .builder()
                    .players(new ArrayList<>())
                    .playerNum(0)
                    .build();
        }

        List<PlayerDtoWithId> players = playerDtoConverter
                .convert(repository.findPlayersBySurnameStartsWithIgnoreCase(surnameLike));
        return PlayersPage
                .builder()
                .players(players)
                .playerNum(players.size())
                .build();
    }

    @Override
    public PlayersPage findPlayerTeammates(Long playerId) {
        List<PlayerDtoWithId> players = playerDtoConverter
                .convert(playerTeammatesRepository.getTeammates(playerId));

        return PlayersPage.builder()
                .players(players)
                .playerNum(players.size())
                .build();
    }

    @Override
    public TeamsPage findTeams(Long playerId) {
        Player player = getOrElseThrow(playerId);
        List<TeamDto> teams = player.getTeams()
                .stream()
                .map(teamMapper::fromTeam)
                .toList();
        return TeamsPage
                .builder()
                .teams(teams)
                .teamsNum(teams.size())
                .build();
    }
    @Override
    public GamesPage getGamesByPlayerId(Long playerId) {
        List<Game> games = gamesByRepository.getGamesByUserId(playerId);
        List<GameDto> gameDtoList = gameDtoConverter.convert(games);
        return GamesPage.builder()
                .games(gameDtoList)
                .gamesNum(gameDtoList.size())
                .build();
    }

    private Player getOrElseThrow(Long playerId) {
        return repository.findById(playerId)
                .orElseThrow(() -> new NotFoundException("No player with id: <" + playerId + "> found"));
    }

}
