package com.Nexa_Forge_Innovations.Journal.App.Controller;

import com.Nexa_Forge_Innovations.Journal.App.Entity.JournalEntry;
import com.Nexa_Forge_Innovations.Journal.App.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryServices journalEntryServices;
    @GetMapping()
    public ResponseEntity<JournalEntry>getAll(){
        List<JournalEntry> journalEntries = journalEntryServices.getAll();
        if(journalEntries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(journalEntries, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
        try {
            entry.setDate(java.time.LocalDateTime.now());
            Optional<JournalEntry>journalEntry = Optional.ofNullable(journalEntryServices.getById(entry.getId()));
            return new ResponseEntity<>(journalEntry.isPresent() ? journalEntry.get() : journalEntryServices.updateEntry(entry), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId){
        try {
            Optional<JournalEntry>journalEntry = Optional.ofNullable(journalEntryServices.getById(myId));
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myId}")
    public  ResponseEntity<JournalEntry>deleteById(@PathVariable ObjectId myId){
        try {
            Optional<JournalEntry>journalEntry = Optional.ofNullable(journalEntryServices.getById(myId));
            journalEntryServices.deleteEntryByid(myId);
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id , @RequestBody JournalEntry entry){
        JournalEntry oldEntry = journalEntryServices.getById(id);
        try {
            entry.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : oldEntry.getTitle());
            entry.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : oldEntry.getContent());
            return new ResponseEntity<>(journalEntryServices.updateEntry(entry), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
