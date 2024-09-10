package de.ricardo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRep extends MongoRepository<Entry, String> {
}
