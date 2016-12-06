import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGuestViewFrame extends HotelReservationFrame {
	
	private static final long serialVersionUID = 1215316L;
	private JPanel frameCenterPanel;
	private JPanel frameCenterPanelNorth;
	
	public MainGuestViewFrame(HotelReservationFrameManager frameManager, HotelManager hotelManager) {
		super(hotelManager);

		this.frameCenterPanel = new JPanel();
		this.frameCenterPanelNorth = new JPanel();
		
		JPanel headerPanel = new JPanel(new FlowLayout());

		JButton backButton = new JButton("Sign Out");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameManager.showFrame(new GuestLogInFrame(frameManager, hotelManager));
			}
		});
		JButton makeReservationButton = new JButton("Make a Reservation");
		makeReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMakeReservationView();
			}
		});
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		headerPanel.add(backButton);
		headerPanel.add(makeReservationButton);
		headerPanel.add(viewOrCancelReservationButton);

		this.add(headerPanel, BorderLayout.NORTH);
		this.add(frameCenterPanel, BorderLayout.CENTER);
		
	}


	/**
	 * Shows the make reservation view for guest.
	 */
	private void showMakeReservationView() {

		frameCenterPanel.removeAll();
		frameCenterPanelNorth.removeAll();

		JPanel textFieldPanel = new JPanel();
		JPanel roomChoicePanel = new JPanel();

		frameCenterPanelNorth.setLayout(new BorderLayout());
		textFieldPanel.setLayout(new GridLayout(4, 2));

		JLabel checkInLabel = new JLabel(" Check in");
		JLabel checkOutLabel = new JLabel("  Check out");
		JTextField dateFrom = new JTextField(CalendarGridFrame.getDateString(new Date()));
		dateFrom.setEditable(false);
		dateFrom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateFrom);
				gridFrame.setVisible(true);
			}
		});
		JTextField dateTo = new JTextField(CalendarGridFrame.getDateString(new Date()));
		dateTo.setEditable(false);
		dateTo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateTo);
				gridFrame.setVisible(true);
			}
		});

		JLabel roomTypeLabel = new JLabel("Room type:");
		JButton luxuriousRoomButton = new JButton("$" + LuxuriousRoom.getPrice());
		luxuriousRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRoomAvailabilityView(false, dateFrom.getText(), dateTo.getText());
			}
		});

		JButton economicRoomButton = new JButton("$" + EconomicRoom.getPrice());
		economicRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRoomAvailabilityView(true, dateFrom.getText(), dateTo.getText());
			}
		});

		roomChoicePanel.add(roomTypeLabel);
		roomChoicePanel.add(luxuriousRoomButton);
		roomChoicePanel.add(economicRoomButton);

		textFieldPanel.add(checkInLabel);
		textFieldPanel.add(checkOutLabel);
		textFieldPanel.add(dateFrom);
		textFieldPanel.add(dateTo);
		textFieldPanel.add(roomChoicePanel);
		frameCenterPanelNorth.add(textFieldPanel, BorderLayout.NORTH);
		frameCenterPanel.add(frameCenterPanelNorth);
		
		frameCenterPanel.validate();
		frameCenterPanel.repaint();

	}

	private void showRoomAvailabilityView(boolean economic, String dateFrom, String dateTo) {
		
		JPanel availabilityViewPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel roomNumberPanel = new JPanel();
		JPanel groupRoomNumberAndButtonsPanel = new JPanel();

		JTextArea availabilityTextArea = new JTextArea("Available Rooms " + dateFrom + " – " + dateTo);

		final int textAreaSize = 300;
		availabilityTextArea.setPreferredSize(new Dimension(textAreaSize, textAreaSize));

		JLabel roomNumberLabel = new JLabel("Enter the room number to reserve.");
		JTextField roomNumberTextField = new JTextField("");
		JButton confirmButton = new JButton("Confirm");
		JButton moreReservationsButton = new JButton("More Reservations?");
		JButton doneButton = new JButton("Done");

		availabilityViewPanel.setLayout(new BorderLayout());
		groupRoomNumberAndButtonsPanel.setLayout(new GridLayout(8, 1));

		roomNumberPanel.add(roomNumberLabel);
		roomNumberPanel.add(roomNumberTextField);

		buttonsPanel.add(confirmButton);
		buttonsPanel.add(moreReservationsButton);
		buttonsPanel.add(doneButton);

		groupRoomNumberAndButtonsPanel.add(roomNumberPanel);
		groupRoomNumberAndButtonsPanel.add(buttonsPanel);

		availabilityViewPanel.add(availabilityTextArea, BorderLayout.CENTER);
		availabilityViewPanel.add(groupRoomNumberAndButtonsPanel, BorderLayout.EAST);

		frameCenterPanelNorth.add(availabilityViewPanel, BorderLayout.CENTER);
		frameCenterPanel.add(frameCenterPanelNorth);
		
		frameCenterPanel.validate();
		frameCenterPanel.repaint();

	}
}
