package br.com.restful.udemy.controller;

/**
 * Importações especiais do HETOAS
 */
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import br.com.restful.udemy.data.vo.PersonVO;
import br.com.restful.udemy.services.PersonService;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(produces = { "application/json", "application/xml" })
	public List<PersonVO> findAll() {
		List<PersonVO> personsVO = service.findAll();

		// link do HETOAS (links de relacionamentos)
		personsVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return personsVO;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = service.findById(id);

		// link do HETOAS (links de relacionamentos)
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		PersonVO personVO = service.create(PersonVO);

		// link do HETOAS (links de relacionamentos)
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@PutMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public PersonVO update(@RequestBody PersonVO personVO) {
		PersonVO personVOAtualizado = service.update(personVO);

		// link do HETOAS (links de relacionamentos)
		personVOAtualizado.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVOAtualizado;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
