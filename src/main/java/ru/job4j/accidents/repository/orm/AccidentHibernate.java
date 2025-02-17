package ru.job4j.accidents.repository.orm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.orm.AccidentORM;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository<AccidentORM> {

    private final SessionFactory sf;

    public AccidentORM save(AccidentORM accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Session session = sf.openSession()) {
            int query = session
                    .createQuery("DELETE FROM AccidentORM WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            return query > 0;
        }
    }

    @Override
    public boolean update(AccidentORM accident) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            int query = session
                    .createQuery("UPDATE AccidentORM SET " +
                            "name = :fName, text = :fText, address = :fAddress  " +
                            "WHERE id = :fId")
                    .setParameter("fName", accident.getName())
                    .setParameter("fText", accident.getText())
                    .setParameter("fAddress", accident.getAddress())
                    .setParameter("fId", accident.getId())
                    .executeUpdate();
            tx.commit();
            return query > 0;
        }
    }

    @Override
    public Optional<AccidentORM> findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentORM where id = :fId", AccidentORM.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Collection<AccidentORM> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentORM", AccidentORM.class)
                    .list();
        }
    }
}
