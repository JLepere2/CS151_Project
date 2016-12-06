import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * The system manager for the program.
 * Contains the main method to run the program.
 * @author JLepere2
 * Version 1.1
 */
public class HotelReservationSystem {

	public static String savedDataSerializationFile = "Saved.dat";
	
	/**
	 * The main method to run the hotel reservation program.
	 * @param args the command line arguments. Not used.
	 */
	public static void main(String[] args) {
		
		File f = new File(savedDataSerializationFile);
		HotelManager manager = new HotelManager();
		try {
			if (f.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				manager = (HotelManager) in.readObject();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GuestAccount.setGuestAccountCount(manager.getGuestAccountSize());
		
		HotelReservationFrameManager frameManager = new HotelReservationFrameManager(manager);
		frameManager.begin();
	}
	
}
