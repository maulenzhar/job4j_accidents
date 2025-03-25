package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.Job4jAccidentsApplication;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest(classes = Job4jAccidentsApplication.class)
@AutoConfigureMockMvc
@Transactional
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnListPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("statements/list"));
    }

    @Test
    @WithMockUser
    public void shouldReturnAccident() throws Exception {
        this.mockMvc.perform(get("/accident/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("statements/one"));
    }

    @Test
    @WithMockUser
    public void shouldReturnUpdatingPage() throws Exception {
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("statements/update"));
    }
}