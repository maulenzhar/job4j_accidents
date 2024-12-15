package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem implements AccidentTypeRepository {

    private int idCounter = 1;

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        save(new AccidentType(0, "Две машины"));
        save(new AccidentType(0, "Машина и человек"));
        save(new AccidentType(0, "Машина и велосипед"));

    }

    @Override
    public AccidentType save(AccidentType accidentType) {
        accidentType.setId(idCounter++);
        accidentTypes.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypes.remove(id) != null;
    }

    @Override
    public boolean update(AccidentType accidentType) {
        return accidentTypes.computeIfPresent(accidentType.getId(), (id, oldAccident) -> {
            return new AccidentType(oldAccident.getId(), accidentType.getName());
        })  != null;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }
}
