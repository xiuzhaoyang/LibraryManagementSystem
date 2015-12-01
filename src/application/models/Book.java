package application.models;

import java.util.List;

public class Book {
	String bId;
	
	String title;
	String ISBN;
	List<Author> authors;
	
	AllowedBorrowDays  bookDay;
	
	List<BookCopy> bookCopies;
}
