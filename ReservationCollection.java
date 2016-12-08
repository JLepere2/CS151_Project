import java.io.Serializable;
import java.util.ArrayList;

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
	
	public void addReservation(Reservation r) {
		reservations.add(r);
	}
	
	public String toString() {
		return this.getClass().getName() + reservations.toString();
	}
	
	/**
	 * Gets a list of available rooms for the provided date.
	 * @param theDate the date.
	 * @return a list of available rooms.
	 */
	public ArrayList<HotelRoom> getAvailableRooms(ArrayList<Reservation> resQueue, MyDate theInDate, MyDate theOutDate) {
		
		for(Reservation r : reservations) {
			System.out.println(r.getTime()+r.getHotelRoom().getRoomNumber()+r.getHotelRoom().getRoomType());
		}
		
		ArrayList<HotelRoom> conflictingHotelRooms = new ArrayList<>();
		
		for (Reservation res : reservations) {
			MyDate resInDate = res.getCheckInDate();
			MyDate resOutDate = res.getCheckOutDate();
			int inCompIn = theInDate.compareTo(resInDate);
			int outCompOut = theOutDate.compareTo(resOutDate);
			int inCompOut = theInDate.compareTo(resOutDate);
			int outCompIn = theOutDate.compareTo(resInDate);
			if ((inCompIn >= 0 && inCompOut <= 0) || (outCompOut <= 0 && outCompIn >= 0) || (inCompIn <= 0 && outCompOut >= 0)) {
				// Conflicting Dates
				conflictingHotelRooms.add(res.getHotelRoom());
			}
		}
		for (Reservation r : resQueue) {
			if (doesConflict(theInDate, theOutDate, r.getCheckInDate(), r.getCheckOutDate())) {
				conflictingHotelRooms.add(r.getHotelRoom());
			}
		}
		
		ArrayList<HotelRoom> roomsAvailable = new ArrayList<>();
		for (int e = 1; e <= ECONOMIC_ROOMS; e ++) {
			boolean available = true;
			for (HotelRoom r : conflictingHotelRooms) {
				if (r.getRoomType().equals(EconomicRoom.identifier) && r.getRoomNumber() == e) {
					System.out.println("FOUND");
					available = false;
				}
			}
			if (available) {
				roomsAvailable.add(new EconomicRoom(e));
			}
		}
		for (int l = 1; l <= LUXURIOUS_ROOMS; l ++) {
			boolean available = true;
			for (HotelRoom r : conflictingHotelRooms) {
				if (r.getRoomType().equals(LuxuriousRoom.identifier) && r.getRoomNumber() == l) {
					System.out.println("FOUND");
					available = false;
				}
			}
			if (available) {
				roomsAvailable.add(new LuxuriousRoom(l));
			}
		}
		
		return roomsAvailable;
		
	}
	
	public boolean doesConflict(MyDate s1, MyDate e1, MyDate s2, MyDate e2) {
		int inCompIn = s1.compareTo(s2);
		int outCompOut = e1.compareTo(e2);
		int inCompOut = s1.compareTo(e2);
		int outCompIn = e1.compareTo(s2);
		if ((inCompIn >= 0 && inCompOut <= 0) || (outCompOut <= 0 && outCompIn >= 0) || (inCompIn <= 0 && outCompOut >= 0)) {
			// Conflicting Dates
			return true;
		}
		return false;
	}
	
}
