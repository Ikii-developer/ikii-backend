package mx.ikii.commons.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class helps with general Date utilities in the transaction business
 * logic
 * 
 * @author Arturo Isaac Velázquez
 *
 */
public class DateHelper {

	/**
	 * 
	 * @param date used to convert to LocalDate
	 * @return the local date variable used in the business logic
	 */
	public static LocalDate fromDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 
	 * @param localDate used to calculate the starting week day based on the
	 *                  assessment requirements, starting on Friday.When the
	 *                  calculation after the Subtraction turns out to be in the
	 *                  previous month, the first day of the month is the starting
	 *                  day of the week
	 * @return the local date after the calculation
	 */
	public static LocalDate getLocalDatefromFirstDayOfWeek(LocalDate localDate) {

		Month month = localDate.getMonth();
		int minusDays = 0;
		// Friday is not considered
		switch (localDate.getDayOfWeek()) {
		case MONDAY:
			minusDays = 3;
			break;
		case TUESDAY:
			minusDays = 4;
			break;
		case WEDNESDAY:
			minusDays = 5;
			break;
		case THURSDAY:
			minusDays = 6;
			break;
		case SATURDAY:
			minusDays = 1;
			break;
		case SUNDAY:
			minusDays = 2;
			break;
		default:
			break;
		}

		if (month.equals(localDate.minusDays(minusDays).getMonth())) {
			localDate = localDate.minusDays(minusDays);
			return localDate;
		} else {
			return localDate = LocalDate.of(localDate.getYear(), month, 1);
		}
	}

	/**
	 * 
	 * @param localDate used to calculate the end week day based on the assessment
	 *                  requirements, ending on Thursday.When the calculation after
	 *                  the addition turns out to be in the next month, the last day
	 *                  of the week is the end day of the month
	 * @return the local date after the calculation
	 */
	public static LocalDate getLocalDatefromLastDayOfWeek(LocalDate localDate) {

		Month month = localDate.getMonth();
		int plusDays = 0;
		// Thursday is not considered
		switch (localDate.getDayOfWeek()) {
		case MONDAY:
			plusDays = 3;
			break;
		case TUESDAY:
			plusDays = 2;
			break;
		case WEDNESDAY:
			plusDays = 1;
			break;
		case SATURDAY:
			plusDays = 5;
			break;
		case SUNDAY:
			plusDays = 4;
			break;
		case FRIDAY:
			plusDays = 6;
			break;
		default:
			break;
		}
		if (month.equals(localDate.plusDays(plusDays).getMonth())) {
			localDate = localDate.plusDays(plusDays);
			return localDate;
		} else {
			return localDate = LocalDate.of(localDate.getYear(), month, YearMonth.now().atEndOfMonth().getDayOfMonth());
		}
	}

}
