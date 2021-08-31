package com.kollinchen.mvc.services;

import java.util.List;
import java.util.Optional;

//...
import org.springframework.stereotype.Service;

import com.kollinchen.mvc.models.Book;
import com.kollinchen.mvc.repositories.BookRepository;
@Service
public class BookService {
 // adding the book repository as a dependency
	private final BookRepository bookRepository;
 
	 public BookService(BookRepository bookRepository) {
	     this.bookRepository = bookRepository;
	 }
	 // returns all the books
	 public List<Book> allBooks() {
	     return bookRepository.findAll();
	 }
	 // creates a book
	 public Book createBook(Book b) {
	     return bookRepository.save(b);
	 }
	 // retrieves a book
	 public Book findBook(Long id) {
		 return bookRepository.findById(id).orElse(null);
//	     Optional<Book> optionalBook = bookRepository.findById(id);
//	     if(optionalBook.isPresent()) {
//	         return optionalBook.get();
//	     } else {
//	         return null;
//	     }
	 }
	 public Book updateBook(Book book) {
		 bookRepository.save(book);
		 return book;
	 }
	 
	 public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		 Book book = this.findBook(id);
		 book.setLanguage(lang);
		 book.setNumberOfPages(numOfPages);
		 book.setTitle(title);
		 book.setDescription(desc);
		 bookRepository.save(book);
		 return book;
	 }
	 // Delete a book
	 public void deleteBook(Long id) {
		 bookRepository.deleteById(id);
	 }
}

