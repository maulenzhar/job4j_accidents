package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {
    private int idCounter = 1;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        save(new Accident(0, "Accident 1", "Lorem ipsum test", "Lorem ipsum address"));
        save(new Accident(0, "Accident 2", "Lorem ipsum test", "Lorem ipsum address"));
        save(new Accident(0, "Accident 3", "Lorem ipsum test", "Lorem ipsum address"));
        save(new Accident(0, "Accident 4", "Lorem ipsum test", "Lorem ipsum address"));
        save(new Accident(0, "Accident 5", "Lorem ipsum test", "Lorem ipsum address"));
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Accident save(Accident accident) {
        accident.setId(idCounter++);
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean deleteById(int id) {
        return accidents.remove(id) != null;
    }

    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) -> {
            return new Accident(oldAccident.getId(), accident.getName(),
                    accident.getText(), accident.getAddress());
        })  != null;
    }
}
