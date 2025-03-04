package ru.job4j.accidents.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.entity.RuleEntity;

import java.util.Collection;

@Repository
public interface RuleRepo extends CrudRepository<RuleEntity, Integer> {
    Collection<RuleEntity> findAll();
}
