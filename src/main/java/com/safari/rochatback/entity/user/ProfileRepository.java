package com.safari.rochatback.entity.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, ObjectId> {
    Optional<Profile> findByUsername(@Param("username") String username);
}
