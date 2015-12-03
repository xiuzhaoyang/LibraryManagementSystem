package application.models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry  implements Serializable{
	private  int eId; //entry id
	private  int bookId; // book id
	private  LocalDate checkoutDate; // borrow date
	private  LocalDate dueDate; // date should give back
	private 	LocalDate returnDate; // actual give back date
	private  boolean isFine;
	private float finePaid;

	public CheckoutEntry(){
		this(0, 0, null, null, null, false, 0.0f);
	}

	public CheckoutEntry(int eId, int bookId, LocalDate checkoutDate, LocalDate dueDate, LocalDate returnDate, boolean isFine, float finePaid){
		this.eId = eId;
		this.bookId = bookId;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.isFine = isFine;
		this.finePaid = finePaid;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isFine() {
		return isFine;
	}

	public void setFine(boolean isFine) {
		this.isFine = isFine;
	}

	public float getFinePaid() {
		return finePaid;
	}

	public void setFinePaid(float finePaid) {
		this.finePaid = finePaid;
	}


}
