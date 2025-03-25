package ru.job4j.accidents.service;

import java.util.Optional;

public interface UserService<T> {
    Optional<T> save(T user);
}
