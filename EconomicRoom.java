/**
 * An economic hotel room.
 * @author JLepere2
 * Version 1.1
 */
public class EconomicRoom extends HotelRoom {
	
	private static int PRICE = 80;
	
	/**
	 * Creates an Economic Room
	 */
	public EconomicRoom() {
		
	}

	public static int getPrice() {
		return PRICE;
	}
	
}
