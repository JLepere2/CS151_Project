import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A date for the hotel reservations
 * @author JLepere2
 * Version 1.1
 */
public class MyDate implements Comparable<MyDate>, Serializable {

	private static final long serialVersionUID = 15325699L;
	private int year, month, day;
	
	public MyDate(int theYear, int theMonth, int theDay) {
		this.year = theYear;
		this.month = theMonth;
		this.day = theDay;
	}
	
	public int compareTo(MyDate otherDate) {
		if (year - otherDate.year != 0) {
			return year - otherDate.year;
		} else if (month - otherDate.month != 0) {
			return month - otherDate.month;
		}
		return day - otherDate.day;
	}
	
	public static MyDate getDate(String d) {
		
		String[] x = d.split("/");
		return new MyDate(Integer.parseInt(x[2]), Integer.parseInt(x[0]), Integer.parseInt(x[1]));
		
	}
	
	public static String getDateString(MyDate d) {
		String theMonth = "";
		if (d.month < 10) {
			theMonth += "0";
		}
		theMonth += d.month;
		String theDay = "";
		if (d.day < 10) {
			theDay += "0";
		}
		theDay += d.day;
		return theMonth + "/" + theDay + "/" +d.year;
	}
	
	public static MyDate getDate(Date d) {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTime(d);
		return new MyDate(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH)+1, temp.get(Calendar.DAY_OF_MONTH));
	}
	
}
