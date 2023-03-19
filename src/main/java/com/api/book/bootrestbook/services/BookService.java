package com.api.book.bootrestbook.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entity.Book;

@Service
public class BookService{
//	private static List<Book> list = new ArrayList<>();
//
//	static {
//		list.add(new Book(12, "Java low level", "XYZ1"));
//		list.add(new Book(36, "lets learn java", "XYZ2"));
//		list.add(new Book(42, "Java high level", "XYZ3"));
//
//	}

	@Autowired
	private BookRepository bookRepository;

	// get all books
	public List<Book> getAllBooks() {
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}

	// get single book by id
	public Book getBookById(int id) {
		Book book = null;
		try {
			// book = list.stream().filter(e -> e.getId() == id).findFirst().get();
			book = this.bookRepository.findById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public Book addBook(Book book) {
		Book result = bookRepository.save(book);
		return result;
	}

	public void deleteBook(int bid) {

//		list = list.stream().filter(book -> {
//			if (book.getId() != bid) {
//				return true;
//			}
//			return false;
//		}).collect(Collectors.toList());
		// writing above thing in one line
		// list = list.stream().filter(book -> book.getId() !=
		// bid).collect(Collectors.toList());
		bookRepository.deleteById(bid);
	}

	public void updateBook(Book book, int bookId) {
		// TODO Auto-generated method stub
//		list = list.stream().map(b -> {
//			if (b.getId() == bookId) {
//				b.setAuthor(book.getAuthor());
//				b.setTitle(book.getTitle());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bookId);
		bookRepository.save(book);
	}

}
