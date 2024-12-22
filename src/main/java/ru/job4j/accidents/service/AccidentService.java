package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentService {
    Accident save(Accident accident, String[] ruleIds);

    boolean deleteById(int id);

    boolean update(Accident accident, String[] ruleIds);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();
}
