package demo.kaiac.springboot.api.repository;

import org.springframework.data.repository.CrudRepository;

import demo.kaiac.springboot.api.pojo.Member;

// This will be AUTO IMPLEMENTED by Spring into a Bean called memberRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {

}