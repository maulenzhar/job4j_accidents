package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem implements RuleRepository<Rule> {

    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        save(new Rule(0, "Статья. 1"));
        save(new Rule(0, "Статья. 2"));
        save(new Rule(0, "Статья. 3"));
    }

    @Override
    public Rule save(Rule rule) {
        rule.setId(idCounter.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public boolean deleteById(int id) {
        return rules.remove(id) != null;
    }

    @Override
    public boolean update(Rule rule) {
        return rules.computeIfPresent(rule.getId(), (id, oldAccident) -> {
            return new Rule(oldAccident.getId(), rule.getName());
        })  != null;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }
}
