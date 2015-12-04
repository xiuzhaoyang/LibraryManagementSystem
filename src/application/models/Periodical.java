package application.models;

import java.io.Serializable;

public class Periodical  implements PublicationType, Serializable {
	private static final long serialVersionUID = 1109L;
	
	public Periodical(){

	}

	@Override
	public String getType() {
		return "Periodical";
	}

	@Override
	public String toString(){
		return "Periodical";
	}

}
