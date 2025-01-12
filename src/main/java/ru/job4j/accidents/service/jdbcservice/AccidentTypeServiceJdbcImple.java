package ru.job4j.accidents.service.jdbcservice;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
public class AccidentTypeServiceJdbcImple implements AccidentTypeService {
    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;

    @Override
    public AccidentType save(AccidentType accidentType) {
        return accidentTypeJdbcTemplate.save(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypeJdbcTemplate.deleteById(id);
    }

    @Override
    public boolean update(AccidentType accidentType) {
        return accidentTypeJdbcTemplate.update(accidentType);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeJdbcTemplate.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeJdbcTemplate.findAll();
    }
}
