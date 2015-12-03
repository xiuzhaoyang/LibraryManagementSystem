package application.models;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable{
	String bId;

	String title;
	String ISBN;
	List<Author> authors;

	AllowedBorrowDays  bookDay;

	List<BookCopy> bookCopies;

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public AllowedBorrowDays getBookDay() {
		return bookDay;
	}

	public void setBookDay(AllowedBorrowDays bookDay) {
		this.bookDay = bookDay;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public Book(String bId, String title, String ISBN, List<Author> authors, AllowedBorrowDays bookDay, List<BookCopy> bookCopies){
		this.bId = bId;
		this.title = title;
		this.ISBN = ISBN;
		this.authors = authors;
		this.bookDay = bookDay;
		this.bookCopies = bookCopies;
	}
	
	public String getAuthorsString(){
		StringBuilder sb = new StringBuilder();
		
		if(authors != null){
			for(Author a: authors){
				sb.append(a.getFirstName()).append(" ").append(a.getLastName());
			}
				
		}
		
		return sb.toString();
	}
	
	private static final long serialVersionUID = 103L;
}
