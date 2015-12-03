package application.models;

import java.io.Serializable;

public class Administrator implements PersonRole, Serializable{
	public String toString(){
		return Person.PERSON_TYPE_ADMIN;
	}
}
