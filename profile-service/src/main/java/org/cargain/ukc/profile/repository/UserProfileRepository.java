package org.cargain.ukc.profile.repository;

import org.cargain.ukc.profile.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

}
