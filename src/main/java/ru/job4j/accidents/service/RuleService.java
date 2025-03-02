package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RuleService<T> {
    T save(T rule);

    boolean deleteById(int id);

    boolean update(T rule);

    Optional<T> findById(int id);

    Collection<T> findAll();
}
