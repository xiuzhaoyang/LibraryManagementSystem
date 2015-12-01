package application.models;

import java.io.Serializable;

public class LibraryMember extends Person implements Serializable{
	public static final String FILENAME = "LibraryMember";

	
	public LibraryMember(){
		super();
	}
	
	public LibraryMember(String fName, String lName, Address ad, String phoneNum){
		super(fName, lName, ad, phoneNum);
		
	}
	
	@Override
	public String toString() {
	
		// need more implementation
		
		return firstName + lastName + phoneNum;
	}
	
	private static final long serialVersionUID = 100373637282828L;
}
