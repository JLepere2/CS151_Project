import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserSelectionFrame extends HotelReservationFrame {

	private static final long serialVersionUID = 11636123L;

	public UserSelectionFrame(final HotelReservationFrameManager frameManager, final HotelManager hotelManager) {
		super(hotelManager);
		
		JPanel headerPanel = new JPanel(new FlowLayout());
		
		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameManager.showFrame(new ManagerLogInFrame(frameManager, hotelManager));
			}
		});

		JButton guestButton = new JButton("GUEST");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameManager.showFrame(new GuestLogInFrame(frameManager, hotelManager));
			}
		});
		
		headerPanel.add(managerButton);
		headerPanel.add(guestButton);
		this.add(headerPanel, BorderLayout.NORTH);
		
	}
	
}
