package br.com.restful.udemy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.restful.udemy.data.vo.PersonVO;
import br.com.restful.udemy.services.PersonService;

public class PersonController {

	private PersonService service;

	public List<PersonVO> findAll() {
		return service.findAll();
	}

	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@PostMapping
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return service.create(PersonVO);
	}

	@PutMapping
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return service.update(PersonVO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
