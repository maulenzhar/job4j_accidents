package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository<T> {
    T save(T accident);

    boolean deleteById(int id);

    boolean update(T accident);

    Optional<T> findById(int id);

    Collection<T> findAll();
}
