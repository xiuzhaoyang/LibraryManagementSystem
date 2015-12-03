package application.models;

import java.io.Serializable;
import java.util.List;

public class CheckoutRecord implements Serializable{
	String mId;
	
	List<CheckoutEntry> enties;

}
