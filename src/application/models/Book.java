package application.models;

import java.io.Serializable;

public class Book implements PublicationType, Serializable{
	private static final long serialVersionUID = 1105L;

	public Book(){};

	@Override
	public String getType() {
		return "Book";
	}

	@Override
	public String toString(){
		return "Book";
	}

}
