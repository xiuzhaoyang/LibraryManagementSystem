package application.models;

import java.time.LocalDate;
import java.util.List;

public class Book implements PublicationType {
//	private int bId;
//
//	private String title;
//	private String ISBN;
//	private List<Author> authors;
//
//	private AllowedBorrowDays  bookDay;
//
//	private List<BookCopy> bookCopies;
//
//	public int getbId() {
//		return bId;
//	}
//
//	public void setbId(int bId) {
//		this.bId = bId;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getISBN() {
//		return ISBN;
//	}
//
//	public void setISBN(String iSBN) {
//		ISBN = iSBN;
//	}
//
//	public List<Author> getAuthors() {
//		return authors;
//	}
//
//	public void setAuthors(List<Author> authors) {
//		this.authors = authors;
//	}
//
//	public AllowedBorrowDays getBookDay() {
//		return bookDay;
//	}
//
//	public void setBookDay(AllowedBorrowDays bookDay) {
//		this.bookDay = bookDay;
//	}
//
//	public List<BookCopy> getBookCopies() {
//		return bookCopies;
//	}
//
//	public void setBookCopies(List<BookCopy> bookCopies) {
//		this.bookCopies = bookCopies;
//	}
//
//	public Book(int bId, String title, String ISBN, List<Author> authors, AllowedBorrowDays bookDay, List<BookCopy> bookCopies){
//		this.bId = bId;
//		this.title = title;
//		this.ISBN = ISBN;
//		this.authors = authors;
//		this.bookDay = bookDay;
//		this.bookCopies = bookCopies;
//	}



	public Book(){};

	@Override
	public String getType() {
		return "Book";
	}
}
