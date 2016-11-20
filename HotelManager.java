import java.io.Serializable;
import java.util.ArrayList;

/**
 * The manager for the hotel. Contains all user and room information.
 * @author JLepere2
 * Version 1.1
 */
public class HotelManager implements Serializable {
	
	private static final long serialVersionUID = 468356262437L;
	private ReservationCollection reservationCollection;
	private ArrayList<GuestAccount> guestAccounts;
	
	/**
	 * Creates a new HotelManager.
	 */
	public HotelManager() {
		
		this.reservationCollection = new ReservationCollection();
		this.guestAccounts = new ArrayList<>();
		
	}
	
	public String toString() {
		return this.getClass().getName() + "[" + reservationCollection.toString() + guestAccounts.toString() + "]";
	}
	
	/**
	 * Adds a new guest account to the hotel.
	 * @param theFirstName the first name of the guest
	 * @param theLastName the last name of the guest
	 */
	public void addGuestAccount(String theFirstName, String theLastName) {
		guestAccounts.add(new GuestAccount(theFirstName, theLastName));
	}
	
	/**
	 * Gets if a user has the id number for signing in.
	 * @param theIdNumber the id number
	 * @return true if there is a user with the id number
	 */
	public boolean validIdForLogIn(int theIdNumber) {
		
		for (GuestAccount account : guestAccounts) {
			if (account.getId() == theIdNumber) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Gets the number of guest accounts.
	 * @return the number of guest accounts
	 */
	public int getGuestAccountSize() {
		return guestAccounts.size();
	}
	
}
