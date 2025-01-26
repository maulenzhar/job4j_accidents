package ru.job4j.accidents.repository.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AccidentJdbcTemplateTest {

    @Autowired
    private AccidentJdbcTemplate accidentJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CREATE TABLE accident (id INT AUTO_INCREMENT, name VARCHAR(255), text VARCHAR(255), address VARCHAR(255), type_id INT, PRIMARY KEY (id))");
        jdbcTemplate.execute("CREATE TABLE accident_rule (accident_id INT, rule_id INT, PRIMARY KEY (accident_id, rule_id))");
    }

    @Test
    void saveShouldInsertAccidentAndRules() {
        AccidentType accidentType = new AccidentType(1, "Type 1");
        Rule rule1 = new Rule(1, "Rule 1");
        Rule rule2 = new Rule(2, "Rule 2");

        Accident accident = new Accident();
        accident.setName("Test Accident");
        accident.setText("Test Description");
        accident.setAddress("Test Address");
        accident.setType(accidentType);
        accident.setRule(List.of(rule1, rule2));

        accidentJdbcTemplate.save(accident);

        Integer countAccidents = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM accident", Integer.class);
        assertEquals(1, countAccidents);

        Integer countRules = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM accident_rule", Integer.class);
        assertEquals(2, countRules);
    }
}