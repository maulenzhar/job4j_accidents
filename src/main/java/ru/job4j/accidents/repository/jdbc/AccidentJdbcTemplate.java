package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {
    private final JdbcTemplate jdbc;

    @Qualifier("accidentTypeJdbcTemplate")
    private final AccidentTypeRepository accidentTypeRepository;

    public Accident save(Accident accident) {
        String sql = "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int saved = jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        if (saved == 1) {
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            String sqlAccidentRule = "INSERT INTO accident_rule(accident_id, rule_id) VALUES (?, ?)";
            for (Rule rule : accident.getRule()) {
                jdbc.update(sqlAccidentRule, id, rule.getId());
            }
        }

        return accident;
    }

    @Override
    public boolean deleteById(int id) {
        int deleted = jdbc.update("delete from accident where id = ?", id);
        return deleted != -1;
    }

    @Override
    public boolean update(Accident accident) {
        int updated = jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return updated != -1;
    }

    @Override
    public Optional<Accident> findById(int id) {
        List<Accident> accidents = jdbc.query("select * from accident where id = ?",
                (rs, row) -> {

                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    Optional<AccidentType> type = accidentTypeRepository.findById(rs.getInt("type_id"));
                    if (type.isPresent()) {
                        accident.setType(type.get());
                    } else {
                        accident.setType(null);
                    }
                    return accident;
                }, id);

        Optional<Accident> accident = accidents.stream().findFirst();
        if (accident.isPresent()) {
            List<Rule> rules = jdbc.query("select r.id, r.name " +
                            "from rule r " +
                            "join accident_rule ar on ar.rule_id = r.id " +
                            "where ar.accident_id = ?",
                    (rs, row) -> {
                        Rule rule = new Rule();
                        rule.setId(rs.getInt("id"));
                        rule.setName(rs.getString("name"));
                        return rule;
                    }, accident.get().getId());

            accident.get().setRule(rules);
        }

        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("select id, name, text, address from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }
}
