package ru.job4j.accidents.repository.orm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.orm.AccidentORM;
import ru.job4j.accidents.model.orm.AccidentTypeORM;
import ru.job4j.accidents.model.orm.RuleORM;
import ru.job4j.accidents.repository.*;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AccidentHibernateTest {

    @Autowired
    private AccidentRepository<AccidentORM> accidentRepository;
    @Autowired
    private AccidentTypeRepository<AccidentTypeORM> accidentTypeRepository;
    @Autowired
    private RuleRepository<RuleORM> ruleRepository;


    @Test
    @Transactional
    void testSave() {
        AccidentTypeORM accidentTypeORM = new AccidentTypeORM();
        accidentTypeORM.setName("Test Type");
        AccidentTypeORM accidentType = accidentTypeRepository.save(accidentTypeORM);

        RuleORM ruleORM = new RuleORM();
        ruleORM.setName("Test Rule");
        RuleORM rule = ruleRepository.save(ruleORM);

        AccidentORM accident = new AccidentORM();
        accident.setName("Integration Test Accident");
        accident.setText("Accident details for integration test");
        accident.setAddress("Test Address");
        accident.setType(accidentType);
        accident.setRule(Collections.singletonList(rule));

        AccidentORM saved = accidentRepository.save(accident);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

}