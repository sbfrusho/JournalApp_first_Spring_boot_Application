package com.Nexa_Forge_Innovations.Journal.App.Controller;

import com.Nexa_Forge_Innovations.Journal.App.Entity.JournalEntry;
import com.Nexa_Forge_Innovations.Journal.App.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryServices journalEntryServices;
    @GetMapping()
    public List<JournalEntry>getAll(){
        return  journalEntryServices.getAll();
    }
    @PostMapping()
    public JournalEntry createEntry(@RequestBody JournalEntry entry){
        entry.setDate(java.time.LocalDateTime.now());
        journalEntryServices.saveEntry(entry);
        return entry;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId){
        return journalEntryServices.getById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryServices.deleteEntryByid(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id , @RequestBody JournalEntry entry){
        JournalEntry oldEntry = journalEntryServices.getById(id);
        if(oldEntry != null){
            entry.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : oldEntry.getTitle());
            entry.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : oldEntry.getContent());
        }
        return journalEntryServices.updateEntry(entry);
    }
}
