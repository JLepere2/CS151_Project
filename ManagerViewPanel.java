import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Rodion on 12/7/16.
 */
public class ManagerViewPanel extends JPanel {

	private static String identifier = "ManagerViewPanel";
	private static final long serialVersionUID = 1215316L;
	private ArrayList<HotelRoom> availableRoomsEconomic;
	private ArrayList<HotelRoom> availableRoomsLuxurious;

	public ManagerViewPanel(MainCardPanel mainCardPanel, final HotelManager hotelManager) {

		// ------Instantiate Panel
		this.setLayout(new GridLayout(2, 2, 20, 20));
		this.setBorder(new EmptyBorder(20, 20, 100, 20));

		// ------Date Range Model
		final DateRangeReservationModel dateRangeModel = new DateRangeReservationModel(hotelManager);

		// ------Instance Variables
		setRoomsAvailable(dateRangeModel);

		// ------ Room Available Text Area
		final JTextArea roomAvailabilityArea = new JTextArea("Room Availability: " + "\n\n" + "Economic Rooms Left: "
				+ availableRoomsEconomic.size() + "\n\n" + "Luxurious Rooms Left: " + availableRoomsLuxurious.size());
		final JTextArea roomInformation = new JTextArea("Information about the room: ");

		// ------ Room View Panel
		JPanel roomView = new JPanel(new GridLayout(4, 1));
		JLabel economicRoomInfo = new JLabel("Economic Room Information: ");
		final JPanel economicRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		JLabel luxuriousRoomInfo = new JLabel("Luxurious Room Information: ");
		final JPanel luxuriousRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation);
		roomView.add(economicRoomInfo);
		roomView.add(economicRoomPanel);
		roomView.add(luxuriousRoomInfo);
		roomView.add(luxuriousRoomPanel);

		// ----- Grid Component
		final JLabel currentDayLabel = new JLabel();
		final CalendarGridComponent gridComp = new CalendarGridComponent.Manager(dateRangeModel, currentDayLabel);
		dateRangeModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setRoomsAvailable(dateRangeModel);
				setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation);
				roomAvailabilityArea.setText(
						"Room Availability: " + "\n\n" + "Economic Rooms Left: " + availableRoomsEconomic.size()
								+ "\n\n" + "Luxurious Rooms Left: " + availableRoomsLuxurious.size());
				roomAvailabilityArea.repaint();
			}
		});

		// ------NORTH GRID PANEL------//
		JPanel gridPanel = new JPanel(new BorderLayout());
		currentDayLabel.setText(gridComp.getCurrentDayLong());
		JPanel nextPreviousPanel = new JPanel(new FlowLayout());
		JButton previousMonthButton = new JButton("   <   ");
		previousMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(false);
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
			}
		});
		JButton nextMonthButton = new JButton("   >   ");
		nextMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(true);
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
			}
		});
		nextPreviousPanel.add(previousMonthButton);
		nextPreviousPanel.add(nextMonthButton);
		gridPanel.add(nextPreviousPanel, BorderLayout.NORTH);
		gridPanel.add(gridComp, BorderLayout.CENTER);
		gridPanel.add(currentDayLabel, BorderLayout.SOUTH);

		// Add Components
		this.add(gridPanel);
		this.add(roomAvailabilityArea);
		this.add(roomView);
		this.add(roomInformation);
	}

	/**
	 * Gets the state identifier for this state
	 * 
	 * @return the state id for this state
	 */
	public static String getStateIdentifier() {
		return identifier;
	}

	/**
	 * Sets the room panels with the current date range
	 * 
	 * @param economicRoomPanel
	 *            the economic room panel
	 * @param luxuriousRoomPanel
	 *            the luxurious room panel
	 * @param roomInformation
	 *            the room information text are
	 */
	private void setRoomPanels(JPanel economicRoomPanel, JPanel luxuriousRoomPanel, final JTextArea roomInformation) {

		// Remove Components
		for (Component c : economicRoomPanel.getComponents()) {
			economicRoomPanel.remove(c);
		}
		for (Component c : luxuriousRoomPanel.getComponents()) {
			luxuriousRoomPanel.remove(c);
		}

		// Economic Room
		for (int i = 1; i <= 10; i++) {
			JButton button = new JButton(i + "");
			button.setForeground(Color.red);
			boolean available = false;
			for (HotelRoom room : availableRoomsEconomic) {
				if (room.getRoomNumber() == i) {
					button.setForeground(Color.green);
					available = true;
				}
			}
			final String roomInfoText;
			if (available) {
				roomInfoText = "Room is available.";
			} else {
				roomInfoText = "Room is occupied.";
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(roomInfoText);
				}
			});
			economicRoomPanel.add(button);
		}
		// Lux Room
		for (int i = 1; i <= 10; i++) {
			JButton button = new JButton(i + "");
			button.setForeground(Color.red);
			boolean available = false;
			for (HotelRoom room : availableRoomsLuxurious) {
				if (room.getRoomNumber() == i) {
					button.setForeground(Color.green);
					available = true;
				}
			}
			final String roomInfoText;
			if (available) {
				roomInfoText = "Room is available.";
			} else {
				roomInfoText = "Room is occupied.";
			}
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(roomInfoText);
				}
			});
			luxuriousRoomPanel.add(button);
		}

		// Repaint
		economicRoomPanel.revalidate();
		luxuriousRoomPanel.revalidate();
		roomInformation.repaint();
	}

	/**
	 * Sets the available rooms from the model
	 * 
	 * @param dateRangeModel
	 *            the date range model
	 */
	private void setRoomsAvailable(DateRangeReservationModel dateRangeModel) {
		dateRangeModel.setRoomSelected(true, false);
		availableRoomsEconomic = dateRangeModel.getReservationAvailable(new ArrayList<Reservation>());
		dateRangeModel.setRoomSelected(false, false);
		availableRoomsLuxurious = dateRangeModel.getReservationAvailable(new ArrayList<Reservation>());
	}

}