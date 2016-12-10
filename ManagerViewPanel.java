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
 * The view panel for the manager
 * @author Rodion Yaryy
 * Version 1.1
 */
public class ManagerViewPanel extends JPanel {

	private static final long serialVersionUID = 1215316L;
	private ArrayList<HotelRoom> availableRoomsEconomic;
	private ArrayList<HotelRoom> availableRoomsLuxurious;

	/**
	 * Creates a ManagerViewPanel.
	 * @param mainCardPanel the main card panel associated with this state panel.
	 * @param hotelManager the hotel manager.
	 */
	public ManagerViewPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

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
		roomAvailabilityArea.setEditable(false);
		final String roomInformationHeader = "Information about the room: ";
		final JTextArea roomInformation = new JTextArea(roomInformationHeader);
		roomInformation.setEditable(false);

		// ------ Room View Panel
		JPanel roomView = new JPanel(new GridLayout(4, 1));
		JLabel economicRoomInfo = new JLabel("Economic Room Information: ");
		final JPanel economicRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		JLabel luxuriousRoomInfo = new JLabel("Luxurious Room Information: ");
		final JPanel luxuriousRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
<<<<<<< HEAD
		setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation);
=======
		setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation, roomInformationHeader, hotelManager.getReservationsByDate(dateRangeModel.getDateTo()));
>>>>>>> JLepere2-patch-2
		roomView.add(economicRoomInfo);
		roomView.add(economicRoomPanel);
		roomView.add(luxuriousRoomInfo);
		roomView.add(luxuriousRoomPanel);

		// ----- Grid Component
		final JLabel currentDayLabel = new JLabel();
<<<<<<< HEAD
		final CalendarGridComponent gridComp = new CalendarGridComponent.Manager(dateRangeModel, currentDayLabel);
=======
		final CalendarGridComponent.Manager gridComp = new CalendarGridComponent.Manager(dateRangeModel, currentDayLabel);
>>>>>>> JLepere2-patch-2
		dateRangeModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setRoomsAvailable(dateRangeModel);
				setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation, roomInformationHeader, hotelManager.getReservationsByDate(new MyDate(gridComp.getCurrentDayShort())));
				roomAvailabilityArea.setText(
						"Room Availability: " + "\n\n" + "Economic Rooms Left: " + availableRoomsEconomic.size()
								+ "\n\n" + "Luxurious Rooms Left: " + availableRoomsLuxurious.size());
				roomAvailabilityArea.repaint();
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
				roomInformation.setText(roomInformationHeader);
				roomInformation.repaint();
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
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
			}
		});
		JButton nextMonthButton = new JButton("   >   ");
		nextMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(true);
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
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
	 * Sets the room panels with the current date range
	 * @param economicRoomPanel the economic room panel
	 * @param luxuriousRoomPanel the luxurious room panel
	 * @param roomInformation the room information text are
	 */
<<<<<<< HEAD
	private void setRoomPanels(JPanel economicRoomPanel, JPanel luxuriousRoomPanel, final JTextArea roomInformation) {
=======
	private void setRoomPanels(JPanel economicRoomPanel, JPanel luxuriousRoomPanel, final JTextArea roomInformation, String roomInformationHeader, ArrayList<Reservation> reservationsForDay) {
>>>>>>> JLepere2-patch-2

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
			String roomInfoText = roomInformationHeader;
			if (available) {
				roomInfoText += "\n\nRoom is available.";
			} else {
				roomInfoText += "\n\nRoom is occupied.";
			}
			for (Reservation r : reservationsForDay) {
				if (r.getHotelRoom().getRoomType().equals(EconomicRoom.identifier) && i == r.getHotelRoom().getRoomNumber()) {
					roomInfoText += "\n\nGuest Name: " + r.getGuestName() + "\nGuest Id: " +  r.getGuestId() + "\nLength of Reservation: " + r.getTime();
				}
			}
			final String theInfo = roomInfoText;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(theInfo);
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
			String roomInfoText = roomInformationHeader;
			if (available) {
				roomInfoText += "\n\nRoom is available.";
			} else {
				roomInfoText += "\n\nRoom is occupied.";
			}
			for (Reservation r : reservationsForDay) {
				if (r.getHotelRoom().getRoomType().equals(LuxuriousRoom.identifier) && i == r.getHotelRoom().getRoomNumber()) {
					roomInfoText += "\n\nGuest Name: " + r.getGuestName() + "\nGuest Id: " +  r.getGuestId() + "\nLength of Reservation: " + r.getTime();
				}
			}
			final String theInfo = roomInfoText;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(theInfo);
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
	 * Sets the available rooms from the model.
	 * @param dateRangeModel the date range model.
	 */
	private void setRoomsAvailable(DateRangeReservationModel dateRangeModel) {
		dateRangeModel.setRoomSelected(true, false);
<<<<<<< HEAD
		availableRoomsEconomic = dateRangeModel.getReservationAvailable(new ArrayList<Reservation>());
		dateRangeModel.setRoomSelected(false, false);
		availableRoomsLuxurious = dateRangeModel.getReservationAvailable(new ArrayList<Reservation>());
=======
		availableRoomsEconomic = dateRangeModel.getAvailableRooms(new ArrayList<Reservation>());
		dateRangeModel.setRoomSelected(false, false);
		availableRoomsLuxurious = dateRangeModel.getAvailableRooms(new ArrayList<Reservation>());
>>>>>>> JLepere2-patch-2
	}

}