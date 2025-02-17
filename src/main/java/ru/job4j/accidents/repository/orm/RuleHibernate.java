package ru.job4j.accidents.repository.orm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.orm.AccidentTypeORM;
import ru.job4j.accidents.model.orm.RuleORM;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository<RuleORM> {

    private final SessionFactory sf;

    @Override
    public RuleORM save(RuleORM rule) {
        try (Session session = sf.openSession()) {
            session.save(rule);
            return rule;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Session session = sf.openSession()) {
            int query = session
                    .createQuery("DELETE FROM RuleORM WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            return query > 0;
        }
    }

    @Override
    public boolean update(RuleORM rule) {
        try (Session session = sf.openSession()) {
            int query = session
                    .createQuery("UPDATE RuleORM SET "
                            + "name = :fName "
                            + "WHERE id = :fId")
                    .setParameter("fName", rule.getName())
                    .executeUpdate();
            return query > 0;
        }
    }

    @Override
    public Optional<RuleORM> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from RuleORM where id = :fId", RuleORM.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Collection<RuleORM> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from RuleORM", RuleORM.class)
                    .list();
        }
    }
}
