package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.entity.AccidentEntity;
import ru.job4j.accidents.model.entity.RuleEntity;
import ru.job4j.accidents.repository.data.AccidentRepo;
import ru.job4j.accidents.repository.data.RuleRepo;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentServiceImplData implements AccidentService<AccidentEntity> {

    private final AccidentRepo accidentRepository;
    private final RuleRepo ruleRepository;

    @Override
    public AccidentEntity save(AccidentEntity accident, String[] ruleIds) {
        accident.setRule(getRules(ruleIds));
        return accidentRepository.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        accidentRepository.deleteById(id);
        return !accidentRepository.existsById(id);
    }

    @Override
    public boolean update(AccidentEntity accident, String[] ruleIds) {
        return accidentRepository.save(accident) != null;
    }

    @Override
    public Optional<AccidentEntity> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<AccidentEntity> findAll() {
        return (Collection<AccidentEntity>) accidentRepository.findAll();
    }

    private List<RuleEntity> getRules(String[] ruleIds) {
        List<RuleEntity> rules = new ArrayList<>();
        for (String id : ruleIds) {
            Optional<RuleEntity> rule = ruleRepository.findById(Integer.parseInt(id));
            rule.ifPresent(rules::add);
        }
        return rules;
    }

}
