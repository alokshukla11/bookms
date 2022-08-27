package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Book;
import demo.response.APIResponse;
import demo.service.BookService;

@RestController
public class BookController
{
    @Autowired
    BookService bookService;

    @GetMapping("/book")
    private APIResponse getAllBook()
    {
        return  bookService.getAllBooks();
    }

    @GetMapping("/book/{bookid}")
    private APIResponse getBook(@PathVariable("bookid") int bookId)
    {
        return bookService.getBookById(bookId);
    }
    
    @GetMapping("/book/isbn/{isbn}")
    private APIResponse findByIsbn(@PathVariable("isbn") String isbn)
    {
        return bookService.findByIsbn(isbn);
    }

    @DeleteMapping("/book/{bookid}")
    private APIResponse deleteBook(@PathVariable("bookid") int bookId)
    {
        return bookService.delete(bookId);
    }

    @PostMapping("/book")
    private APIResponse saveBook(@RequestBody Book book)
    {
        return bookService.saveOrUpdate(book);
    }


}
