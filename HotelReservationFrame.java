import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class HotelReservationFrame extends JFrame {

	private static final long serialVersionUID = 113L;
	private static int FRAME_WIDTH = 650;
	private static int FRAME_HEIGHT = 500;
	private static String TITLE = "HOTEL RESERVATION SYSTEM";
	private HotelManager manager;
	
	public HotelReservationFrame(final HotelManager manager) {
		this.manager = manager;
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(TITLE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(HotelReservationSystem.savedDataSerializationFile)));
					out.writeObject(manager);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public HotelManager getManager() {
		return manager;
	}
	
}
