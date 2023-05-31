package ru.itis.judgeassistant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.cort.CourtsPage;
import ru.itis.judgeassistant.dto.cort.NewCortDto;
import ru.itis.judgeassistant.exceptions.NotFoundException;
import ru.itis.judgeassistant.models.Cort;
import ru.itis.judgeassistant.repositories.CortRepository;
import ru.itis.judgeassistant.services.CortService;
import ru.itis.judgeassistant.services.converters.mapstruct.CortMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CortServiceImpl implements CortService {
    private final CortRepository cortRepository;
    private final CortMapper cortMapper;

    @Override
    public CortDto save(NewCortDto newCort) {
        Cort cort = new Cort();
        cort.setNumber(newCort.getNumber());
        cortRepository.save(cort);
        return cortMapper.fromCort(cort);
    }

    @Override
    public CortDto findById(Long id) {
        Cort cort = getOrElseThrow(id);

        return cortMapper.fromCort(cort);
    }

    @Override
    public CortDto update(Long cortId, CortDto cortUpdate) {
        Cort cort = getOrElseThrow(cortId);

        cort.setNumber(cortUpdate.getNumber());

        cortRepository.save(cort);
        return cortMapper.fromCort(cort);
    }

    @Override
    public void delete(Long cortId) {
        Cort cort = getOrElseThrow(cortId);
        cortRepository.delete(cort);
    }

    @Override
    public CourtsPage findAll() {
        List<CortDto> courts = cortRepository.findAll()
                .stream()
                .map(cortMapper::fromCort)
                .toList();
        CourtsPage page = CourtsPage
                .builder()
                .courts(courts)
                .courtsNum(courts.size())
                .build();

        return page;
    }

    private Cort getOrElseThrow(Long cortId) {
        return cortRepository.findById(cortId)
                .orElseThrow(() -> new NotFoundException("No cort with id: <" + cortId + "> found"));
    }
}
