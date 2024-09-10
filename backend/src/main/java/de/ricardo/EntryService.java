package de.ricardo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepo entryRepo;

    public List<Entry> getEntry() {
        return entryRepo.findAll();
    }

    public Entry save(Entry entry) {
        Entry NewEntry = new Entry(UUID.randomUUID().toString(), entry.name(), entry.entry());
        return entryRepo.save(entry);
    }

}
