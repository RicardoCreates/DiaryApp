package de.ricardo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepo extends MongoRepository<Entry, String> {
}
