package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.team.TeamDto;
import ru.itis.judgeassistant.models.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDto fromTeam(Team team);

//    @Mapping(target = "id", ignore = true)
//    Team fromDto(TeamDto teamDto);
}
