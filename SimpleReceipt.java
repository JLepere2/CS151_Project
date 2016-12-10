import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple receipt.
 * @author JLepere2
 * Version 1.1
 */
public class SimpleReceipt implements ReceiptFormat {

	public String getReceipt(GuestAccount guestAccount, ArrayList<Reservation> reservations) {
		String receipt = "User id: " + guestAccount.getId() + "\n";
		receipt += "Name: " + guestAccount.getLastName() + ", " + guestAccount.getFirstName() + "\n\n";
		int total = 0;
		Collections.sort(reservations);
		for (Reservation r : reservations) {
			HotelRoom room = r.getHotelRoom();
			receipt += room.getRoomType() + room.getRoomNumber() + " " + r.getTime() + ": $" + room.getPrice() + "\n";
			total += room.getPrice();
		}
		receipt += "\nTotal cost: " + total;
		return receipt;
	}

}
