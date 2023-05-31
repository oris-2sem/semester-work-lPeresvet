package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.CoachDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.simpleentities.Coach;
import ru.itis.judgeassistant.repositories.CoachRepository;
import ru.itis.judgeassistant.services.CoachService;
import ru.itis.judgeassistant.services.converters.mapstruct.CoachMapper;

@RequiredArgsConstructor
@Service
public class CoachServiceImpl implements CoachService {
    private final CoachRepository repository;
    private final CoachMapper coachMapper;

    @Override
    public CoachDto save(CoachDto newCoach) {
        Coach coach = coachMapper.fromDto(newCoach);

        repository.save(coach);

        return coachMapper.fromCoach(coach);
    }

    @Override
    public CoachDto findById(Long id) {
        Coach coach = getOrElseThrow(id);
        return coachMapper.fromCoach(coach);
    }

    @Override
    public CoachDto update(Long coachId, CoachDto coachUpdate) {
        Coach coach = getOrElseThrow(coachId);

        coach.setName(coachUpdate.getName());
        coach.setSurname(coachUpdate.getSurname());
        coach.setPatronymic(coachUpdate.getPatronymic());

        repository.save(coach);
        return coachMapper.fromCoach(coach);
    }

    @Override
    public void delete(Long coachId) {
        Coach coach = getOrElseThrow(coachId);

        repository.delete(coach);
    }

    private Coach getOrElseThrow(Long coachId) {
        return repository.findById(coachId)
                .orElseThrow(() -> new NotFoundException("No coach with id: <" + coachId + "> found"));
    }

}
