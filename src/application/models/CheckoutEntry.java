package application.models;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CheckoutEntry {
	private final IntegerProperty eId; //entry id
	private final ObjectProperty<Book> book; // book id
	private final ObjectProperty<LocalDate> checkoutDate; // borrow date
	private final ObjectProperty<LocalDate> dueDate; // date should give back
	private final ObjectProperty<LocalDate> returnDate; // actual give back date
	private final BooleanProperty isFine;
	private final FloatProperty finePaid;

	public CheckoutEntry(){
		this(0, null, null, null, null, false, 0.0f);
	}

	public CheckoutEntry(int eId, Book book, LocalDate checkoutDate, LocalDate dueDate, LocalDate returnDate, boolean isFine, float finePaid){
		this.eId = new SimpleIntegerProperty(eId);
		this.book = new SimpleObjectProperty<Book>(book);
		this.checkoutDate = new SimpleObjectProperty<LocalDate>(checkoutDate);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		this.returnDate = new SimpleObjectProperty<LocalDate>(returnDate);
		this.isFine = new SimpleBooleanProperty(isFine);
		this.finePaid = new SimpleFloatProperty(finePaid);
	}

	public int getEntryId(){
		return this.eId.get();
	}

	public void setEntryId(int eId){
		this.eId.set(eId);
	}

	public IntegerProperty entryIdProperty(){
		return eId;
	}

	public Book getBook(){
		return this.book.get();
	}

	public void setBook(Book book){
		this.book.set(book);
	}

	public ObjectProperty<Book> bookProperty(){
		return book;
	}

	public LocalDate getCheckoutDate(){
		return this.checkoutDate.get();
	}

	public void setCheckoutDate(LocalDate checkoutDate){
		this.checkoutDate.set(checkoutDate);
	}

	public ObjectProperty<LocalDate> checkoutProperty(){
		return checkoutDate;
	}

	public LocalDate getDueDate(){
		return this.dueDate.get();
	}

	public void setDueDate(LocalDate dueDate){
		this.checkoutDate.set(dueDate);
	}

	public ObjectProperty<LocalDate> dueDateProperty(){
		return dueDate;
	}

	public LocalDate getReturnDate(){
		return this.returnDate.get();
	}

	public void setReturnDate(LocalDate returnDate){
		this.returnDate.set(returnDate);
	}

	public ObjectProperty<LocalDate> returnDateProperty(){
		return returnDate;
	}

	public Boolean getIsFine(){
		return this.isFine.get();
	}

	public void setIsFine(Boolean isFine){
		this.isFine.set(isFine);
	}

	public BooleanProperty returnIsFineProperty(){
		return isFine;
	}

	public Float getFinePaid(){
		return this.finePaid.get();
	}

	public void setFinePaid(Float finePaid){
		this.finePaid.set(finePaid);
	}

	public FloatProperty returnFinePaidProperty(){
		return finePaid;
	}
}
