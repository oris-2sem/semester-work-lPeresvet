package ru.itis.judgeassistant.services.converters.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.cort.NewCortDto;
import ru.itis.judgeassistant.models.Cort;

@Mapper(componentModel = "spring")
public interface CortMapper {
    CortDto fromCort(Cort cort);

    @Mapping(target = "games", ignore = true)
    Cort fromDto(CortDto cortDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "games", ignore = true)
    Cort fromNewDto(NewCortDto cortDto);
}
