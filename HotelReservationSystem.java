import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

/**
 * The system manager for the program.
 * Contains the main method to run the program.
 * @author JLepere2
 * Version 1.1
 */
public class HotelReservationSystem {

	private static String savedDataSerializationFile = "Saved.dat";
	
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
		
		final HotelReservationFrame frame = new HotelReservationFrame(manager);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(savedDataSerializationFile)));
					out.writeObject(frame.hotelManager);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
		
	}
	
}
