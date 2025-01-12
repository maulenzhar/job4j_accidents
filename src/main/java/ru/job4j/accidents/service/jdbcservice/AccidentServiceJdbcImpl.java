package ru.job4j.accidents.service.jdbcservice;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.repository.RuleRepository;
import ru.job4j.accidents.repository.jdbc.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
public class AccidentServiceJdbcImpl implements AccidentService {
    private final AccidentJdbcTemplate accidentsRepository;
    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;
    private final RuleRepository ruleRepository = new RuleMem();

    @Override
    public Accident save(Accident accident, String[] ruleIds) {
        accident.setRule(getRules(ruleIds));
        setType(accident);
        return accidentsRepository.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentsRepository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident, String[] ruleIds) {
        accident.setRule(getRules(ruleIds));
        setType(accident);
        return accidentsRepository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentsRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentsRepository.findAll();
    }

    private void setType(Accident accident) {
        AccidentType type = accidentTypeJdbcTemplate.findById(accident.getType().getId())
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