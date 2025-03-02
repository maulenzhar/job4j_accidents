package ru.job4j.accidents.repository.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.entity.AccidentEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AccidentRepoTest {
    @Autowired
    private AccidentRepo accidentRepo;

    @Test
    @Transactional
    void testSave() {
        AccidentEntity accident = new AccidentEntity();
        accident.setId(1);

        accidentRepo.save(accident);
        Optional<AccidentEntity> foundAccident = accidentRepo.findById(1);

        assertThat(foundAccident).isPresent();

    }
}