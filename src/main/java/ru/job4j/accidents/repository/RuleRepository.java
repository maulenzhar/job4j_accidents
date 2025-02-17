package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RuleRepository<T> {
    T save(T rule);

    boolean deleteById(int id);

    boolean update(T rule);

    Optional<T> findById(int id);

    Collection<T> findAll();
}
