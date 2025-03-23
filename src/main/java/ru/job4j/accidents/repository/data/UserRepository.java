package ru.job4j.accidents.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}