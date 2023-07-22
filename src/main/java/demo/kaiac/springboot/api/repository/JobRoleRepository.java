package demo.kaiac.springboot.api.repository;

import org.springframework.data.repository.CrudRepository;

import demo.kaiac.springboot.api.pojo.JobRole;

// This will be AUTO IMPLEMENTED by Spring into a Bean called memberRepository
// CRUD refers Create, Read, Update, Delete

public interface JobRoleRepository extends CrudRepository<JobRole, Integer> {

}