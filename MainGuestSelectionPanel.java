import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGuestSelectionPanel extends JPanel {

	private static final long serialVersionUID = 11531L;

	public MainGuestSelectionPanel(DateRangeReservationModel dateRangeModel, MainGuestAvailabilityPanel availabilityPanel) {

		//-----INPUT DATE PANEL----///
		JPanel textFieldPanel = new JPanel(new GridLayout(4, 2));
		JLabel checkInLabel = new JLabel(" Check in");
		JLabel checkOutLabel = new JLabel(" Check out");
		JTextField dateFrom = new JTextField(MyDate.getDateString(MyDate.getDate(new Date())));
		dateFrom.setEditable(false);
		dateFrom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateFrom);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(true, dateFrom.getText());
					}
				});
			}
		});
		JTextField dateTo = new JTextField(MyDate.getDateString(MyDate.getDate(new Date())));
		dateTo.setEditable(false);
		dateTo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateTo);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(false, dateTo.getText());
					}
				});
			}
		});
		textFieldPanel.add(checkInLabel);
		textFieldPanel.add(checkOutLabel);
		textFieldPanel.add(dateFrom);
		textFieldPanel.add(dateTo);

		//----ROOM CHOICE PANEL----
		JPanel roomChoicePanel = new JPanel();
		JLabel roomTypeLabel = new JLabel("Room type:");
		JButton luxuriousRoomButton = new JButton("$" + LuxuriousRoom.getPrice());
		luxuriousRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(false);
				availabilityPanel.setVisible(true);
				availabilityPanel.repaint();
			}
		});

		JButton economicRoomButton = new JButton("$" + EconomicRoom.getPrice());
		economicRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(true);
				availabilityPanel.setVisible(true);
				availabilityPanel.repaint();
			}
		});
		roomChoicePanel.add(roomTypeLabel);
		roomChoicePanel.add(luxuriousRoomButton);
		roomChoicePanel.add(economicRoomButton);

		textFieldPanel.add(roomChoicePanel);
		this.add(textFieldPanel, BorderLayout.NORTH);

	}

}
