package br.com.restful.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.restful.udemy.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
