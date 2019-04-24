package br.com.restful.udemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restful.udemy.model.Person;
import br.com.restful.udemy.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping
	public List<Person> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public Person findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}

	@PutMapping
	public Person update(@RequestBody Person person) {
		return service.create(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
