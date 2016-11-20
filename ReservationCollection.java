import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * A manager for the rooms.
 * @author JLepere2
 * Version 1.1
 */
public class ReservationCollection implements Serializable {

	private static final long serialVersionUID = 1000L;
	private static int LUXURIOUS_ROOMS = 10;
	private static int ECONOMIC_ROOMS = 10;
	
	private ArrayList<Reservation> reservations;
	
	/**
	 * Creates a new RoomManager
	 */
	public ReservationCollection() {
		
		this.reservations = new ArrayList<>();
		
	}
	
	public String toString() {
		return this.getClass().getName() + reservations.toString();
	}
	
	/**
	 * Gets a list of available rooms for the provided date.
	 * @param theDate the date.
	 * @return a list of available rooms.
	 */
	public ArrayList<HotelRoom> getAvailableRooms(Date theDate) {
		
		int currentLuxRoomCount = LUXURIOUS_ROOMS;
		int currentEconRoomCount = ECONOMIC_ROOMS;
		
		for (Reservation res : reservations) {
			Date resInDate = res.getCheckInDate();
			Date resOutDate = res.getCheckOutDate();
			if (theDate.compareTo(resInDate) >= 0 && theDate.compareTo(resOutDate) <= 0) {
				// Conflicting Dates
				if (res.getHotelRoom() instanceof LuxuriousRoom) {
					currentLuxRoomCount --;
				} else if (res.getHotelRoom() instanceof EconomicRoom) {
					currentEconRoomCount --;
				}
			}
		}
		
		ArrayList<HotelRoom> roomsAvailable = new ArrayList<>();
		for (int l = 0; l < currentLuxRoomCount; l ++) {
			roomsAvailable.add(new LuxuriousRoom());
		}
		for (int e = 0; e < currentEconRoomCount; e ++) {
			roomsAvailable.add(new EconomicRoom());
		}
		
		return roomsAvailable;
		
	}
	
}
