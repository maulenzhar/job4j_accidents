package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentTypeServiceImpl implements AccidentTypeService {

    private AccidentTypeRepository accidentTypeRepository = new AccidentTypeMem();

    @Override
    public AccidentType save(AccidentType accidentType) {
        return accidentTypeRepository.save(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypeRepository.deleteById(id);
    }

    @Override
    public boolean update(AccidentType accidentType) {
        return accidentTypeRepository.update(accidentType);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
