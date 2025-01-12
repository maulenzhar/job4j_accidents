package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {
    private final JdbcTemplate jdbc;

    @Override
    public AccidentType save(AccidentType accidentType) {
        String sql = "INSERT INTO accident_type (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int saved = jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, accidentType.getName());
            return ps;
        }, keyHolder);

        return accidentType;
    }

    @Override
    public boolean deleteById(int id) {
        int deleted = jdbc.update("delete from accident_type where id = ?", id);
        return deleted != -1;
    }

    @Override
    public boolean update(AccidentType accidentType) {
        int updated = jdbc.update("insert into accident_type (name) values (?)",
                accidentType.getName());
        return updated != -1;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        List<AccidentType> accidentTypes = jdbc.query("select * from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);

        return accidentTypes.stream().findFirst();
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
