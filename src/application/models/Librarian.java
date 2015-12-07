package application.models;

import java.io.Serializable;

public class Librarian implements PersonRole, Serializable{
	public String getPersonRole(){
		return Person.PERSON_TYPE_LIBRARIAN;
	}
}
