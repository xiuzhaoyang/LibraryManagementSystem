package application.models;

import java.util.List;

public class Book {
	String bId;

	String title;
	String ISBN;
	List<Author> authors;

	AllowedBorrowDays  bookDay;

	List<BookCopy> bookCopies;

	public Book(String bId, String title, String ISBN, List<Author> authors, AllowedBorrowDays bookDay, List<BookCopy> bookCopies){
		this.bId = bId;
		this.title = title;
		this.ISBN = ISBN;
		this.authors = authors;
		this.bookDay = bookDay;
		this.bookCopies = bookCopies;
	}
}
