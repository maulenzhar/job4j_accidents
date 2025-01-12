package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Rule save(Rule rule) {
        String sql = "INSERT INTO rule (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int saved = jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, rule.getName());
            return ps;
        }, keyHolder);

        return rule;
    }

    @Override
    public boolean deleteById(int id) {
        int deleted = jdbc.update("delete from rule where id = ?", id);
        return deleted != -1;
    }

    @Override
    public boolean update(Rule rule) {
        int updated = jdbc.update("insert into rule (name) values (?)",
                rule.getName());
        return updated != -1;
    }

    @Override
    public Optional<Rule> findById(int id) {
        List<Rule> rules = jdbc.query("select * from rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);

        return rules.stream().findFirst();
    }

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}
