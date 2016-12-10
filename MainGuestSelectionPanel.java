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

	public MainGuestSelectionPanel(final DateRangeReservationModel dateRangeModel, final MainGuestAvailabilityPanel availabilityPanel) {

		//-----INPUT DATE PANEL----///
		JPanel textFieldPanel = new JPanel(new GridLayout(4, 2));
		JLabel checkInLabel = new JLabel(" Check in");
		JLabel checkOutLabel = new JLabel(" Check out");
		final JTextField dateFrom = new JTextField(MyDate.getDateString(MyDate.getDate(new Date())));
		final JTextField dateTo = new JTextField(MyDate.getDateString(MyDate.getDate(new Date())));
		dateFrom.setEditable(false);
		dateFrom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateFrom, dateTo, true);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(true, dateFrom.getText());
						dateRangeModel.setDate(false, dateTo.getText());
					}
				});
			}
		});
		dateTo.setEditable(false);
		dateTo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateTo, dateFrom, false);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(false, dateTo.getText());
						dateRangeModel.setDate(true, dateFrom.getText());
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
		JButton luxuriousRoomButton = new JButton("$" + LuxuriousRoom.PRICE);
		luxuriousRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(false, true);
				availabilityPanel.setVisible(true);
				availabilityPanel.repaint();
			}
		});

		JButton economicRoomButton = new JButton("$" + EconomicRoom.PRICE);
		economicRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(true, true);
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
