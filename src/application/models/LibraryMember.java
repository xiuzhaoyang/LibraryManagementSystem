package application.models;

import java.io.Serializable;

public class LibraryMember implements Serializable , PersonRole{

	public String toString(){
		return Person.PERSON_TYPE_MEMBER;
	}


	
	private static final long serialVersionUID = 100373637282828L;
}
