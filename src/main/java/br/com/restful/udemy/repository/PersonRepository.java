package br.com.restful.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.restful.udemy.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
