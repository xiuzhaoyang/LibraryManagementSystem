package application.models;

import java.util.Date;

public class CheckoutEntry {
	String bId; // book id
	
	Date checkOut;  //borrow date
	Date dueDate;   //date should give back
	
	Date backDate; // actual give back date
	
	boolean isFined;
	
	float finedPay;
}
