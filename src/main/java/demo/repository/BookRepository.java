package demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import demo.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>
{

	Optional<Book> findByIsbn(String isbn);
}
