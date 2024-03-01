package com.Nexa_Forge_Innovations.Journal.App.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.Nexa_Forge_Innovations.Journal.App.Entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{

}
