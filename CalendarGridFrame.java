import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A frame for the calendar grid to select a date.
 * @author JLepere2
 * Version 1.1
 */
public class CalendarGridFrame extends JFrame {

	private static final long serialVersionUID = 11531L;
	private static int FRAME_SIZE = 300;
	private static String FRAME_TITLE = "Select Date";
	private JTextField textField;
	private ArrayList<ChangeListener> changeListeners;
	
	public CalendarGridFrame(JTextField theTextField) {
		
		this.textField = theTextField;
		this.changeListeners = new ArrayList<>();
		
		this.setTitle(FRAME_TITLE);
		this.setSize(FRAME_SIZE, FRAME_SIZE);
		this.setLocationRelativeTo(null);
		
		CalendarGridComponent gridComp = new CalendarGridComponent(this);
		
		JLabel currentMonthLabel = new JLabel(gridComp.getCurrentMonth());
		currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeListeners.add(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				currentMonthLabel.setText(gridComp.getCurrentMonth());
				currentMonthLabel.repaint();
			}
		});
		
		JPanel topPanel = new JPanel(new FlowLayout());
		JButton previousMonthButton = new JButton("   <   ");
		previousMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(false);
			}
		});
		JButton nextMonthButton = new JButton("   >   ");
		nextMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(true);
			}
		});
		topPanel.add(previousMonthButton);
		topPanel.add(nextMonthButton);
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(gridComp, BorderLayout.CENTER);
		this.add(currentMonthLabel, BorderLayout.SOUTH);
		
	}
	
	public void notifyChange() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : changeListeners) {
			l.stateChanged(e);
		}
	}
	
	public void datePressed(Date d) {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTime(d);
		int year = temp.get(Calendar.YEAR);
		int month = temp.get(Calendar.MONTH) + 1;
		String theMonth = "";
		if (month < 10) {
			theMonth += "0";
		}
		theMonth += month;
		int day = temp.get(Calendar.DAY_OF_MONTH);
		String theDay = "";
		if (day < 10) {
			theDay += "0";
		}
		theDay += day;
		textField.setText(theMonth + "/" + theDay + "/" + year);
		textField.repaint();
		this.dispose();
	}
	
	public static String getDateString(Date d) {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTime(d);
		int year = temp.get(Calendar.YEAR);
		int month = temp.get(Calendar.MONTH) + 1;
		String theMonth = "";
		if (month < 10) {
			theMonth += "0";
		}
		theMonth += month;
		int day = temp.get(Calendar.DAY_OF_MONTH);
		String theDay = "";
		if (day < 10) {
			theDay += "0";
		}
		theDay += day;
		return theMonth + "/" + theDay + "/" + year;
	}
	
}
