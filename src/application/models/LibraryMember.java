package application.models;

import java.io.Serializable;

public class LibraryMember implements Serializable , PersonRole{
	public static final String FILENAME = "LibraryMember";

	public String toString(){
		return "LibraryMember";
	}


	public LibraryMember(){
		super();
	}


	private static final long serialVersionUID = 100373637282828L;
}
