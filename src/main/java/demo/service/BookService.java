package demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Book;
import demo.repository.BookRepository;
import demo.response.APIResponse;
import demo.response.util.APIResponseUtil;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BookService.class);

	public APIResponse getAllBooks() {
		try {
			List<Book> books = new ArrayList<Book>();
			LOGGER.debug("books:{}",books);
			bookRepository.findAll().forEach(book -> books.add(book));
        	if(books!=null && books.size()>0)
        		return APIResponseUtil.successResponse(books);
        	else
        		return APIResponseUtil.notFoudResponse(null);
        }catch(Exception e) {
        	return APIResponseUtil.failResponse(e.getLocalizedMessage());
        }
	}

	public APIResponse getBookById(int bookId) {
		try {
        	if(bookId==0)
        		return APIResponseUtil.badResponse(null);
        	Optional<Book> book =  bookRepository.findById(bookId);
        	if(book!=null && !book.isPresent()) {
        		Book book_ = book.get();
        		return APIResponseUtil.successResponse(book_);
        	}
        	else
        		return APIResponseUtil.notFoudResponse(null);
        }catch(Exception e) {
        	return APIResponseUtil.failResponse(e.getLocalizedMessage());
        }
	}
	
	public APIResponse findByIsbn(String isbn) {
		try {
        	if(isbn==null || isbn.isEmpty())
        		return APIResponseUtil.badResponse(null);
        	Optional<Book> book =  bookRepository.findByIsbn(isbn);
        	if(book!=null && !book.isPresent()) {
        		Book book_ = book.get();
        		return APIResponseUtil.successResponse(book_);
        	}
        	else
        		return APIResponseUtil.notFoudResponse(null);
        }catch(Exception e) {
        	return APIResponseUtil.failResponse(e.getLocalizedMessage());
        }
	}

	public APIResponse saveOrUpdate(Book book) {

		APIResponse apiResponse = APIResponseUtil.notFoudResponse(book);
		try {
			if (book == null)
				return APIResponseUtil.badResponse(null);
			Book book_ = null;
			if(book.getBookid()==0) {
				book_ = bookRepository.save(book);//save new book
				apiResponse = APIResponseUtil.successResponse(book_);
			}else {//update book
				APIResponse getApiResponse = getBookById(book.getBookid());
				//book present in db
				if(getApiResponse.getStatus()==APIResponseUtil.SUCCESS_STATUS) {
					book_ = bookRepository.save(book);
					apiResponse = APIResponseUtil.successResponse(book_);
				}else {
					apiResponse = APIResponseUtil.notFoudResponse(book);
				}
			}	
		} catch (Exception e) {
			return APIResponseUtil.failResponse(e.getLocalizedMessage());
		}
		return apiResponse;
	}

	public APIResponse delete(int bookId) {
		try {
        	if(bookId==0)
        		return APIResponseUtil.badResponse(null);
    		bookRepository.deleteById(bookId);
        	return APIResponseUtil.successResponse(bookId);
        	
        }catch(Exception e) {
        	return APIResponseUtil.failResponse(e.getLocalizedMessage());
        }
	}

}
