import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainGuestHeaderPanel extends JPanel {

	private static final long serialVersionUID = 1531L;

	public MainGuestHeaderPanel(final MainCardPanel mainCardPanel, HotelManager hotelManager, final MainGuestSelectionPanel selectionPanel) {
		
		JButton backButton = new JButton("Sign Out");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
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
