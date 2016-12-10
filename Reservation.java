import java.io.Serializable;

/**
 * A reservation for the hotel.
 * @author JLepere2
 * Version 1.1
 */
public class Reservation implements Serializable {

	private static final long serialVersionUID = 73737828466L;

	private GuestAccount guest;
	private HotelRoom room;
	private MyDate checkInDate, checkOutDate;
	
	/**
	 * Creates a new Reservation.
	 * @param theGuest the guest
	 * @param theRoom the hotel room
	 * @param theCheckInDate the check in date
	 * @param theCheckOutDate the check out date
	 */
	public Reservation(GuestAccount theGuest, HotelRoom theRoom, MyDate theCheckInDate, MyDate theCheckOutDate) {
		this.guest = theGuest;
		this.room = theRoom;
		this.checkInDate = theCheckInDate;
		this.checkOutDate = theCheckOutDate;
	}
	
	/**
	 * Gets the check in date for the reservation.
	 * @return the check in date
	 */
	public MyDate getCheckInDate() {
		return checkInDate;
	}
	
	/**
	 * Gets the check out date for the reservation.
	 * @return the check out date
	 */
	public MyDate getCheckOutDate() {
		return checkOutDate;
	}
	
	/**
	 * Gets the hotel room for the reservation.
	 * @return the hotel room
	 */
	public HotelRoom getHotelRoom() {
		return room;
	}
	
	/**
	 * Gets the time range of this reservation
	 * @return the time range of this reservation
	 */
	public String getTime() {
		return MyDate.getDateString(checkInDate) + " - " + MyDate.getDateString(checkOutDate);
	}
	
}
