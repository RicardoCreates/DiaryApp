package de.ricardo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(EntryController.class)
public class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryService entryService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetEntries() throws Exception {
        Entry entry = new Entry("1", "John", "My first entry");
        when(entryService.getEntry()).thenReturn(Collections.singletonList(entry));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].entry").value("My first entry"));
    }

    @Test
    public void testPostEntry() throws Exception {
        Entry entry = new Entry("1", "John", "My first entry");
        when(entryService.save(entry)).thenReturn(entry);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/diary")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(entry)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.entry").value("My first entry"));
    }
}
