import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
	private ArrayList<ChangeListener> changeListeners;
	
	public CalendarGridFrame(final JTextField theTextField, final JTextField otherTextField, final boolean settingFrom) {
		
		this.changeListeners = new ArrayList<>();
		
		this.setTitle(FRAME_TITLE);
		this.setSize(FRAME_SIZE, FRAME_SIZE);
		this.setLocationRelativeTo(null);
		
		final CalendarGridComponent gridComp = new CalendarGridComponent(this);
		
		//-------SOUTH PANEL-------//
		JPanel southPanel = new JPanel(new BorderLayout());
		final JLabel currentMonthLabel = new JLabel(gridComp.getCurrentDayLong());
		currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeListeners.add(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				currentMonthLabel.setText(gridComp.getCurrentDayLong());
				currentMonthLabel.repaint();
			}
		});
		JButton enterButton = new JButton("ENTER");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (settingFrom) {
					if (shouldSetOther(gridComp.getCurrentDayShort(), otherTextField, settingFrom)) {
						otherTextField.setText(gridComp.getCurrentDayShort());
					}
				} else {
					if (shouldSetOther(gridComp.getCurrentDayShort(), otherTextField, settingFrom)) {
						otherTextField.setText(gridComp.getCurrentDayShort());
					}
				}
				datePressed(theTextField, gridComp.getCurrentDayShort());
			}
		});
		southPanel.add(currentMonthLabel, BorderLayout.NORTH);
		southPanel.add(enterButton, BorderLayout.SOUTH);
		
		//------NORTH PANEL------//
		JPanel northPanel = new JPanel(new FlowLayout());
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
		northPanel.add(previousMonthButton);
		northPanel.add(nextMonthButton);
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(gridComp, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		
	}
	
	public void notifyChange() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : changeListeners) {
			l.stateChanged(e);
		}
	}
	
	private void datePressed(JTextField theTextField, String theDate) {
		theTextField.setText(theDate);
		theTextField.repaint();
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private boolean shouldSetOther(String theTextFieldText, JTextField otherTextField, boolean settingFrom) {
		if (settingFrom) {
			if (MyDate.getDate(theTextFieldText).compareTo(MyDate.getDate(otherTextField.getText())) > 0) {
				return true;
			}
		} else {
			if (MyDate.getDate(theTextFieldText).compareTo(MyDate.getDate(otherTextField.getText())) < 0) {
				return true;
			}
		}
		return false;
	}
	
}
