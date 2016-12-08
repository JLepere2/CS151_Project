import java.io.Serializable;

/**
 * An economic hotel room.
 * @author JLepere2
 * Version 1.1
 */
public class EconomicRoom extends HotelRoom implements Serializable {
	
	private static int PRICE = 80;
	private static final long serialVersionUID = 1253632L;
	public static String identifier = "ECON";
	
	/**
	 * Creates an Economic Room
	 */
	public EconomicRoom(int theRoomNumber) {
		super.roomNumber = theRoomNumber;
	}

	public static int getPrice() {
		return PRICE;
	}
	
	public String getRoomType() {
		return identifier;
	}
	
}
