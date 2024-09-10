package de.ricardo;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @GetMapping()
    public List<Entry> getEntry(){
        return entryService.getEntry();
    }

    @PostMapping
    public Entry postEntry(@RequestBody Entry entry) {
        return entryService.save(entry);
    }

}
