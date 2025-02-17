package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("accidentServiceJdbc")
@RequiredArgsConstructor
public class AccidentServiceImpl implements AccidentService<Accident> {

    private final AccidentRepository<Accident> accidentRepository = new AccidentMem();
    private final AccidentTypeRepository<AccidentType> accidentTypeRepository = new AccidentTypeMem();
    private final RuleRepository<Rule> ruleRepository = new RuleMem();

    @Override
    public Accident save(Accident accident, String[] ruleIds) {
        accident.setRule(getRules(ruleIds));
        setType(accident);
        return (Accident) accidentRepository.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentRepository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident, String[] ruleIds) {
        accident.setRule(getRules(ruleIds));
        setType(accident);
        return accidentRepository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }

    private void setType(Accident accident) {
        AccidentType type = accidentTypeRepository.findById(accident.getType().getId())
                .orElse(accident.getType());
        accident.setType(type);
    }

    private List<Rule> getRules(String[] ruleIds) {
        List<Rule> rules = new ArrayList<>();
        for (String id : ruleIds) {
            Optional<Rule> rule = ruleRepository.findById(Integer.parseInt(id));
            rule.ifPresent(rules::add);
        }
        return rules;
    }
}
