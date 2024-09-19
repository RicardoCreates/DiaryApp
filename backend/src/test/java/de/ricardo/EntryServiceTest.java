package de.ricardo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryServiceTest {

    @Mock
    private EntryRepo entryRepo;

    @InjectMocks
    private EntryService entryService;

    @Test
    public void testGetEntry() {
        Entry entry = new Entry("1", "John", "My first entry");
        when(entryRepo.findAll()).thenReturn(Collections.singletonList(entry));

        List<Entry> entries = entryService.getEntry();
        assertEquals(1, entries.size());
        assertEquals("John", entries.get(0).name());
    }

    @Test
    public void testSave() {
        Entry entry = new Entry("1", "John", "My first entry");
        when(entryRepo.save(any(Entry.class))).thenReturn(entry);

        Entry savedEntry = entryService.save(entry);
        assertEquals("John", savedEntry.name());
        assertEquals("My first entry", savedEntry.entry());
    }
}
