import java.awt.BorderLayout;
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

public class MainGuestAvailabilityPanel extends JPanel {

	private static final long serialVersionUID = 113546L;
	private ArrayList<Reservation> reservationQueue;
	private ArrayList<HotelRoom> availableRooms;
	private static String queueHeader = "Reservation Queue:";

	public MainGuestAvailabilityPanel(GuestAccount currentAccount, DateRangeReservationModel dateRangeModel, HotelManager hotelManager) {
		
		this.availableRooms = new ArrayList<>();
		this.reservationQueue = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		//----Reservation Text Area Queue----//
		JTextArea reservationQueueTextArea = new JTextArea(queueHeader);
		
		//----AVAILABILITY TEXT FIELD
		JTextArea availabilityTextArea = new JTextArea();
		
		//-----ROOM NUMBER PANEL-----//
		JPanel roomNumberPanel = new JPanel();
		JLabel roomNumberLabel = new JLabel("Enter the room number to reserve.");
		JTextField roomNumberTextField = new JTextField("   ");
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
							System.out.println("To: "+dateRangeModel.getDateFrom());
							System.out.println("From: "+dateRangeModel.getDateTo());
							Reservation res = new Reservation(currentAccount, r, MyDate.getDate(dateRangeModel.getDateFrom()), MyDate.getDate(dateRangeModel.getDateTo()));
							currentAccount.addReservation(res);
							hotelManager.addReservation(res);
						}
					}
					if (valid) {
						JOptionPane.showMessageDialog(null, "Successfully Confirmed");
					} else {
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
							reservationQueue.add(new Reservation(currentAccount, r, MyDate.getDate(dateRangeModel.getDateFrom()), MyDate.getDate(dateRangeModel.getDateTo())));
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
					currentAccount.addReservation(r);
					hotelManager.addReservation(r);
					// TODO: receipt
				}
				reservationQueue.clear();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(moreReservationsButton);
		buttonsPanel.add(doneButton);
		
		//-----ROOM AND BUTTOn PANEL-----//
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
				availableRooms = dateRangeModel.getReservationAvailable(reservationQueue);
				String availabilityText = dateRangeModel.getDateRangeHeader();
				for (HotelRoom r : availableRooms) {
					availabilityText += r.getRoomNumber() + "\n";
				}
				availabilityTextArea.setText(availabilityText);
				availabilityTextArea.repaint();
			}
		});
		
	}

}
