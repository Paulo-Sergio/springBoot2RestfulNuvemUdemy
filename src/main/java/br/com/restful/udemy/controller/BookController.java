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

import br.com.restful.udemy.data.vo.BookVO;
import br.com.restful.udemy.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Uma descrição qualquer", tags = {"BookEndpoint"})
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	private BookService service;

	@ApiOperation(value = "Find all books")
	@GetMapping(produces = { "application/json", "application/xml" })
	public List<BookVO> findAll() {
		List<BookVO> booksVO = service.findAll();

		// link do HETOAS (links de relacionamentos)
		booksVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return booksVO;
	}

	@ApiOperation(value = "Find a specific book by your ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = service.findById(id);

		// link do HETOAS (links de relacionamentos)
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}

	@ApiOperation(value = "Create a new book")
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public BookVO create(@RequestBody BookVO BookVO) {
		BookVO bookVO = service.create(BookVO);

		// link do HETOAS (links de relacionamentos)
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}

	@ApiOperation(value = "Update a specific book")
	@PutMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public BookVO update(@RequestBody BookVO bookVO) {
		BookVO bookVOAtualizado = service.update(bookVO);

		// link do HETOAS (links de relacionamentos)
		bookVOAtualizado.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVOAtualizado;
	}

	@ApiOperation(value = "Delete a specific book by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
