package br.com.florluan.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.florluan.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
