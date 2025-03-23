package ru.job4j.accidents.service;

public interface UserService<T> {
    T save(T user);
}
