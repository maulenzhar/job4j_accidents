package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.entity.RuleEntity;
import ru.job4j.accidents.repository.data.RuleRepo;
import ru.job4j.accidents.service.RuleService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleServiceImplData implements RuleService<RuleEntity> {

    private final RuleRepo ruleRepo;

    @Override
    public RuleEntity save(RuleEntity rule) {
        return ruleRepo.save(rule);
    }

    @Override
    public boolean deleteById(int id) {
        ruleRepo.deleteById(id);
        return !ruleRepo.existsById(id);
    }

    @Override
    public boolean update(RuleEntity rule) {
        return ruleRepo.save(rule) != null;
    }

    @Override
    public Optional<RuleEntity> findById(int id) {
        return ruleRepo.findById(id);
    }

    @Override
    public Collection<RuleEntity> findAll() {
        return ruleRepo.findAll();
    }
}
