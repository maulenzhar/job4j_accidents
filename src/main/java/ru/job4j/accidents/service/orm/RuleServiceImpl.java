package ru.job4j.accidents.service.orm;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.orm.RuleORM;
import ru.job4j.accidents.repository.orm.RuleHibernate;
import ru.job4j.accidents.service.RuleService;

import java.util.Collection;
import java.util.Optional;

@Service("ruleOrm")
@Primary
@AllArgsConstructor
public class RuleServiceImpl implements RuleService<RuleORM> {

    private final RuleHibernate ruleHibernate;

    @Override
    public RuleORM save(RuleORM rule) {
        return ruleHibernate.save(rule);
    }

    @Override
    public boolean deleteById(int id) {
        return ruleHibernate.deleteById(id);
    }

    @Override
    public boolean update(RuleORM rule) {
        return ruleHibernate.update(rule);
    }

    @Override
    public Optional<RuleORM> findById(int id) {
        return ruleHibernate.findById(id);
    }

    @Override
    public Collection<RuleORM> findAll() {
        return ruleHibernate.findAll();
    }
}
