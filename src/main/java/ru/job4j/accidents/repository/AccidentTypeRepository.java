package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeRepository<T> {
    T save(T accidentType);

    boolean deleteById(int id);

    boolean update(T accidentType);

    Optional<T> findById(int id);

    Collection<T> findAll();
}
