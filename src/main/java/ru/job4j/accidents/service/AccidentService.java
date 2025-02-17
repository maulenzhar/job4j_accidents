package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentService<T> {
    T save(T accident, String[] ruleIds);

    boolean deleteById(int id);

    boolean update(T accident, String[] ruleIds);

    Optional<T> findById(int id);

    Collection<T> findAll();
}
