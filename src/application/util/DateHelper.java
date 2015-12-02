package application.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDate date){
		if(date == null){
			return null;
		}

		return DATE_FORMATTER.format(date);
	}

	public static LocalDate parse(String date){
		try{
			return DATE_FORMATTER.parse(date, LocalDate::from);
		}catch(DateTimeParseException e){
			return null;
		}
	}

	public static boolean validDate(String date){
		return DateHelper.parse(date) != null;
	}
}
