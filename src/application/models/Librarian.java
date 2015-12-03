package application.models;

import java.io.Serializable;

public class Librarian implements PersonRole, Serializable{
	public String toString(){
		return Person.PERSON_TYPE_LIBRARIAN;
	}
}
