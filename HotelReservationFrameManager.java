/**
 * A class that manages the different frames of the application
 * @author JLepere2
 * Version 1.1
 */
public class HotelReservationFrameManager {
	
	private HotelReservationFrame currentFrame;
	
	public HotelReservationFrameManager(HotelManager manager) {
		currentFrame = new UserSelectionFrame(this, manager);
	}

	public void showFrame(HotelReservationFrame frame) {
		frame.setVisible(true);
		currentFrame.dispose();
		currentFrame = frame;
	}
	
	public void begin() {
		currentFrame.setVisible(true);
	}
	
}
