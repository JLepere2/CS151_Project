/**
 * A room for a hotel.
 * @author JLepere2
 * Version 1.1
 */
public abstract class HotelRoom {

	private int price;

	/**
	 * Creates a hotel room and sets the price of the room.
	 * @param thePrice the price of the room.
	 */
	protected HotelRoom(int thePrice) {
		this.price = thePrice;
	}
	
	/**
	 * Gets the price of the hotel room.
	 * @return the price of the hotel room.
	 */
	public int getPrice() {
		return price;
	}
	
}
