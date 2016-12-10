import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panel for the guest availability view portion of the guest view panel
 * @author JLepere2
 * Version 1.1
 */
public class MainGuestAvailabilityPanel extends JPanel {

	private static final long serialVersionUID = 113546L;
	private ArrayList<Reservation> reservationQueue;
	private ArrayList<HotelRoom> availableRooms;
	private static String queueHeader = "Reservation Queue:";

	/**
	 * Create a MainGuestAvailabilityPanel
	 * @param mainCardPanel the main card panel. Used to access the current account.
	 * @param dateRangeModel the date range model.
	 * @param hotelManager the hotel manager.
	 */
	public MainGuestAvailabilityPanel(final MainCardPanel mainCardPanel, final DateRangeReservationModel dateRangeModel, final HotelManager hotelManager) {
		
		this.availableRooms = new ArrayList<>();
		this.reservationQueue = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		//----Reservation Text Area Queue----//
		final JTextArea reservationQueueTextArea = new JTextArea(queueHeader);
		
		//----AVAILABILITY TEXT FIELD
		final JTextArea availabilityTextArea = new JTextArea();
		
		//-----ROOM NUMBER PANEL-----//
		JPanel roomNumberPanel = new JPanel();
		JLabel roomNumberLabel = new JLabel("Enter the room number to reserve.");
		final JTextField roomNumberTextField = new JTextField("");
		roomNumberTextField.setPreferredSize(new Dimension(40, 20));
		roomNumberPanel.add(roomNumberLabel);
		roomNumberPanel.add(roomNumberTextField);
		
		//------BUTTONS PANEL-----//
		JPanel buttonsPanel = new JPanel();
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int roomSelected = Integer.parseInt(roomNumberTextField.getText().trim());
					boolean valid = false;
					for (HotelRoom r : availableRooms) {
						if (r.getRoomNumber() == roomSelected) {
							valid = true;
							Reservation res = new Reservation(mainCardPanel.getCurrentAccount(), r, MyDate.getDate(dateRangeModel.getDateFrom()), MyDate.getDate(dateRangeModel.getDateTo()));
							mainCardPanel.getCurrentAccount().addReservation(res);
							hotelManager.addReservation(res);
							ArrayList<Reservation> reservations = new ArrayList<>();
							reservations.add(res);
							showReceipt(mainCardPanel.getCurrentAccount(), reservations);
							setAvailabilityTextArea(dateRangeModel, availabilityTextArea);
							roomNumberTextField.setText("");
							roomNumberTextField.repaint();
						}
					}
					if (!valid) {
						JOptionPane.showMessageDialog(null, "Invalid Room Number");
					}
					// TODO: receipt
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid Room Number");
				}

			}
		});
		JButton moreReservationsButton = new JButton("More Reservations?");
		moreReservationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int roomSelected = Integer.parseInt(roomNumberTextField.getText().trim());														
					boolean valid = false;
					for (HotelRoom r : availableRooms) {
						if (r.getRoomNumber() == roomSelected) {
							valid = true;
							reservationQueue.add(new Reservation(mainCardPanel.getCurrentAccount(), r, MyDate.getDate(dateRangeModel.getDateFrom()), MyDate.getDate(dateRangeModel.getDateTo())));
						}
					}
					if (valid) {
						JOptionPane.showMessageDialog(null, "Added Reservation to Queue.");
						String reservationQueueText = queueHeader;
						for (Reservation r : reservationQueue) {
							reservationQueueText += "\n"+r.getHotelRoom().getRoomNumber()+" "+r.getTime();
						}
						reservationQueueTextArea.setText(reservationQueueText);
						setVisible(false);
						repaint();
						reservationQueueTextArea.repaint();
						roomNumberTextField.setText("");
						roomNumberTextField.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Room Number");
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid Room Number");
				}
			}
		});
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Reservation r : reservationQueue) {
					mainCardPanel.getCurrentAccount().addReservation(r);
					hotelManager.addReservation(r);
				}
				showReceipt(mainCardPanel.getCurrentAccount(), reservationQueue);
				reservationQueue.clear();
				reservationQueueTextArea.repaint();
				roomNumberTextField.setText("");
				roomNumberTextField.repaint();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(moreReservationsButton);
		buttonsPanel.add(doneButton);
		
		//-----ROOM AND BUTTON PANEL-----//
		JPanel roomAndButtonPanel = new JPanel(new GridLayout(2, 1));
		roomAndButtonPanel.add(roomNumberPanel);
		roomAndButtonPanel.add(buttonsPanel);
		
		//---- COMBO PANEL -----//
		JPanel comboPanel = new JPanel(new BorderLayout());
		comboPanel.add(roomAndButtonPanel, BorderLayout.NORTH);
		comboPanel.add(new JScrollPane(reservationQueueTextArea), BorderLayout.CENTER);

		//-----ADD TO PANEL----//
		this.add(new JScrollPane(availabilityTextArea), BorderLayout.CENTER);
		this.add(comboPanel, BorderLayout.EAST);

		dateRangeModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setAvailabilityTextArea(dateRangeModel, availabilityTextArea);
			}
		});
		
	}
	
	/**
	 * Sets the availability text are
	 * @param dateRangeModel the date range model
	 * @param availabilityTextArea the availability text are
	 */
	private void setAvailabilityTextArea(DateRangeReservationModel dateRangeModel, JTextArea availabilityTextArea) {
		availableRooms = dateRangeModel.getReservationAvailable(reservationQueue);
		String availabilityText = dateRangeModel.getDateRangeHeader();
		for (HotelRoom r : availableRooms) {
			availabilityText += r.getRoomNumber() + "\n";
		}
		availabilityTextArea.setText(availabilityText);
		availabilityTextArea.repaint();
	}
	
	/**
	 * Clears all the reservations from the queue
	 */
	public void removeAllFromQueue() {
		reservationQueue.clear();
	}
	
	/**
	 * Shows the receipt frame.
	 * @param currentAccount the current guest account
	 * @param reservations an array list of reservations that were just processed.
	 */
	private void showReceipt(GuestAccount currentAccount, ArrayList<Reservation> reservations) {
		ReceiptFrame receiptFrame = new ReceiptFrame(currentAccount, reservations);
		receiptFrame.setVisible(true);
	}

}
