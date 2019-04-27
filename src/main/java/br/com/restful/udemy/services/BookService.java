package br.com.restful.udemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restful.udemy.converter.DozerConverter;
import br.com.restful.udemy.data.model.Book;
import br.com.restful.udemy.data.vo.BookVO;
import br.com.restful.udemy.exception.ResourceNotFoundException;
import br.com.restful.udemy.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public BookVO create(BookVO bookVO) {
		Book entity = DozerConverter.parseObject(bookVO, Book.class);
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public List<BookVO> findAll() {
		List<Book> books = repository.findAll();
		List<BookVO> vos = DozerConverter.parseListObjects(books, BookVO.class);
		return vos;
	}

	public BookVO findById(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public BookVO update(BookVO bookVO) {
		Book entity = repository.findById(bookVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunchDate(bookVO.getLaunchDate());
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());

		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}
}
