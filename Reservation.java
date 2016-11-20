import java.io.Serializable;
import java.util.Date;

/**
 * A reservation for the hotel.
 * @author JLepere2
 * Version 1.1
 */
public class Reservation implements Serializable {

	private static final long serialVersionUID = 73737828466L;

	private GuestAccount guest;
	private HotelRoom room;
	private Date checkInDate;
	private Date checkOutDate;
	
	/**
	 * Creates a new Reservation.
	 * @param theGuest the guest
	 * @param theRoom the hotel room
	 * @param theCheckInDate the check in date
	 * @param theCheckOutDate the check out date
	 */
	public Reservation(GuestAccount theGuest, HotelRoom theRoom, Date theCheckInDate, Date theCheckOutDate) {
		this.guest = theGuest;
		this.room = theRoom;
		this.checkInDate = theCheckInDate;
		this.checkOutDate = theCheckOutDate;
	}
	
	/**
	 * Gets the check in date for the reservation.
	 * @return the check in date
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}
	
	/**
	 * Gets the check out date for the reservation.
	 * @return the check out date
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	
	/**
	 * Gets the hotel room for the reservation.
	 * @return the hotel room
	 */
	public HotelRoom getHotelRoom() {
		return room;
	}
	
}
