package demo.kaiac.springboot.api.repository;

import org.springframework.data.repository.CrudRepository;

import demo.kaiac.springboot.api.pojo.Project;

// This will be AUTO IMPLEMENTED by Spring into a Bean called projectRepository
// CRUD refers Create, Read, Update, Delete

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}