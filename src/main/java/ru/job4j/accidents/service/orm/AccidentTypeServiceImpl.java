package ru.job4j.accidents.service.orm;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.orm.AccidentTypeORM;
import ru.job4j.accidents.repository.orm.AccidentTypeHibernate;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

@Service("accidentTypeServiceOrm")
@Primary
@AllArgsConstructor
public class AccidentTypeServiceImpl implements AccidentTypeService<AccidentTypeORM> {

    private final AccidentTypeHibernate accidentTypeHibernate;

    @Override
    public AccidentTypeORM save(AccidentTypeORM accidentType) {
        return accidentTypeHibernate.save(accidentType);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypeHibernate.deleteById(id);
    }

    @Override
    public boolean update(AccidentTypeORM accidentType) {
        return accidentTypeHibernate.update(accidentType);
    }

    @Override
    public Optional<AccidentTypeORM> findById(int id) {
        return accidentTypeHibernate.findById(id);
    }

    @Override
    public Collection<AccidentTypeORM> findAll() {
        return accidentTypeHibernate.findAll();
    }
}
