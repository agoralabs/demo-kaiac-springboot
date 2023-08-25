package demo.kaiac.springboot.api.repository;

import org.springframework.data.repository.CrudRepository;

import demo.kaiac.springboot.api.pojo.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called UserRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}