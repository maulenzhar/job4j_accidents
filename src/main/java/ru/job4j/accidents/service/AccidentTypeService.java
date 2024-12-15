package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeService {
    AccidentType save(AccidentType accidentType);

    boolean deleteById(int id);

    boolean update(AccidentType accidentType);

    Optional<AccidentType> findById(int id);

    Collection<AccidentType> findAll();
}
