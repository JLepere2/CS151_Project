import java.io.Serializable;

/**
 * A room for a hotel.
 * @author JLepere2
 * Version 1.1
 */
public abstract class HotelRoom implements Serializable, Room {

	private static final long serialVersionUID = 146431L;
	protected int roomNumber;
	
	/**
	 * Gets the price of the hotel room.
	 * @return the price of the hotel room.
	 */
	public static int getPrice() {
		return 0;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
}
