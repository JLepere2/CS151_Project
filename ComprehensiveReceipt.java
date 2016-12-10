import java.util.ArrayList;

/**
 * A comprehensive receipt of all user reservations.
 * @author JLepere2
 * Version 1.1
 */
public class ComprehensiveReceipt implements ReceiptFormat {
	
	public String getReceipt(GuestAccount guestAccount, ArrayList<Reservation> reservations) {
		ArrayList<Reservation> theReservations = new ArrayList<>();
		for (Reservation r : guestAccount.getReservations()) {
			theReservations.add(r);
		}
		String receipt = "User id: " + guestAccount.getId() + "\n";
		receipt += "Name: " + guestAccount.getLastName() + ", " + guestAccount.getFirstName() + "\n\n";
		int total = 0;
		for (Reservation r : theReservations) {
			HotelRoom room = r.getHotelRoom();
			receipt += room.getRoomType() + room.getRoomNumber() + " " + r.getTime() + ": $" + room.getPrice() + "\n";
			total += room.getPrice();
		}
		receipt += "\ntotal cost: " + total;
		return receipt;
	}
	
}
