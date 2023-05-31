package ru.itis.judgeassistant.services;

import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.cort.CourtsPage;
import ru.itis.judgeassistant.dto.cort.NewCortDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Cort;
import ru.itis.judgeassistant.models.users.Judge;
import ru.itis.judgeassistant.repositories.CortRepository;
import ru.itis.judgeassistant.repositories.JudgeRepository;

public interface CortService {
    CortDto save(NewCortDto newCort);
    CortDto findById(Long id);
    CortDto update(Long cortId, CortDto cortUpdate);
    void delete(Long cortId);

    CourtsPage findAll();

    static Cort getOrElseThrow(Long cortId, CortRepository cortRepository) {
        return cortRepository.findById(cortId)
                .orElseThrow(() -> new NotFoundException("No cort with id: <" + cortId + "> found"));
    }
}
