package com.Nexa_Forge_Innovations.Journal.App.services;

import com.Nexa_Forge_Innovations.Journal.App.Entity.JournalEntry;
import com.Nexa_Forge_Innovations.Journal.App.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry>getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id){
        return journalEntryRepository.findById(id).get();
    }

    public void deleteEntryByid(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateEntry(JournalEntry journalEntry){
        return journalEntryRepository.save(journalEntry);
    }
}

//controller -- > services -- > repository
