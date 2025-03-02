package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.entity.AccidentTypeEntity;
import ru.job4j.accidents.repository.data.AccidentTypeRepo;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeServiceImplData implements AccidentTypeService<AccidentTypeEntity> {

    private final AccidentTypeRepo accidentTypeRepo;

    @Override
    public AccidentTypeEntity save(AccidentTypeEntity accidentType) {
        return accidentTypeRepo.save(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        accidentTypeRepo.deleteById(id);
        return !accidentTypeRepo.existsById(id);
    }

    @Override
    public boolean update(AccidentTypeEntity accidentType) {
        return accidentTypeRepo.save(accidentType) != null;
    }

    @Override
    public Optional<AccidentTypeEntity> findById(int id) {
        return accidentTypeRepo.findById(id);
    }

    @Override
    public Collection<AccidentTypeEntity> findAll() {
        return (Collection<AccidentTypeEntity>) accidentTypeRepo.findAll();
    }
}
