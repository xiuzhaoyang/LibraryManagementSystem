package application.models;

import java.time.LocalDate;
import java.util.List;

public class Periodical  implements PublicationType {
	public Periodical(){

	}

	@Override
	public String getType() {
		return "Periodical";
	}

}
