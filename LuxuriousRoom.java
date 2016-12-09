import java.io.Serializable;

/**
 * A luxurious hotel room.
 * @author JLepere2
 * Version 1.1
 */
public class LuxuriousRoom extends HotelRoom implements Serializable {

	private static final long serialVersionUID = 126326L;
	private static int PRICE = 200;
	public static String identifier = "LUX";
	
	/**
	 * Creates a LuxuriousRoom.
	 */
	public LuxuriousRoom(int theRoomNumber) {
		super.roomNumber = theRoomNumber;
	}

	public static int getPrice() {
		return PRICE;
	}
	
	public String getRoomType() {
		return identifier;
	}
	
}
