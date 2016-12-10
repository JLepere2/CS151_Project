import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The header panel for the main guest view.
 * @author JLepere2
 * Version 1.1
 */
public class MainGuestHeaderPanel extends JPanel {

	private static final long serialVersionUID = 1531L;


	/**
	 * Creates a header panel for the guest view.
	 * @param mainCardPanel the main card panel.
	 * @param hotelManager the hotel manager.
	 * @param selectionPanel the guest selection view panel.
	 * @param availabilityPanel the availability view panel.
	 */
	public MainGuestHeaderPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager, final MainGuestSelectionPanel selectionPanel, final MainGuestAvailabilityPanel availabilityPanel) {

		JButton backButton = new JButton("Sign Out");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
				selectionPanel.setVisible(false);
				availabilityPanel.setVisible(false);
				availabilityPanel.removeAllFromQueue();
			}
		});
		JButton makeReservationButton = new JButton("Make a Reservation");
		makeReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionPanel.setVisible(true);
				selectionPanel.repaint();
			}
		});
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.add(backButton);
		this.add(makeReservationButton);
		this.add(viewOrCancelReservationButton);
		
	}
	
}
