package ru.job4j.accidents.repository.orm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.orm.AccidentTypeORM;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository<AccidentTypeORM> {

    private final SessionFactory sf;

    @Override
    public AccidentTypeORM save(AccidentTypeORM accidentType) {
        try (Session session = sf.openSession()) {
            session.save(accidentType);
            return accidentType;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Session session = sf.openSession()) {
            int query = session
                    .createQuery("DELETE FROM AccidentTypeORM WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            return query > 0;
        }
    }

    @Override
    public boolean update(AccidentTypeORM accidentType) {
        try (Session session = sf.openSession()) {
            int query = session
                    .createQuery("UPDATE AccidentTypeORM SET " +
                            "name = :fName " +
                            "WHERE id = :fId")
                    .setParameter("fName", accidentType.getName())
                    .executeUpdate();
            return query > 0;
        }
    }

    @Override
    public Optional<AccidentTypeORM> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentTypeORM where id = :fId", AccidentTypeORM.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Collection<AccidentTypeORM> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentTypeORM", AccidentTypeORM.class)
                    .list();
        }
    }
}
