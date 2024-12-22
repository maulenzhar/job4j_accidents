package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private AccidentRepository accidentRepository = new AccidentMem();
    private AccidentTypeRepository accidentTypeRepository = new AccidentTypeMem();

    @Override
    public Accident save(Accident accident) {
        setType(accident);
        return accidentRepository.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentRepository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        setType(accident);
        return accidentRepository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }

    private void setType(Accident accident) {
        AccidentType type = accidentTypeRepository.findById(accident.getType().getId())
                .orElse(accident.getType());
        accident.setType(type);
    }
}
