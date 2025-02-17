package ru.job4j.accidents.service.orm;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.orm.AccidentORM;
import ru.job4j.accidents.repository.orm.AccidentHibernate;
import ru.job4j.accidents.service.AccidentService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service("accidentServiceOrm")
@Primary
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService<AccidentORM> {

    private final AccidentHibernate accidentsRepostiory;

    @Override
    public AccidentORM save(AccidentORM accident, String[] ruleIds) {
        return accidentsRepostiory.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentsRepostiory.deleteById(id);
    }

    @Override
    public boolean update(AccidentORM accident, String[] ruleIds) {
        return accidentsRepostiory.update(accident);
    }

    @Override
    public Optional<AccidentORM> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    @Override
    public Collection<AccidentORM> findAll() {
        return accidentsRepostiory.findAll();
    }


}
