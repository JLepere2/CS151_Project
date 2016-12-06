import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * A grid of buttons representing a calendar for a month;
 * @author JLepere2
 * Version 1.1
 */
public class CalendarGridComponent extends JComponent {
	
	private static final long serialVersionUID = 12632L;
	private String[] shortDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	private String[] longMonths = {"January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December"};
	private CalendarGridFrame parentFrame;
	private GregorianCalendar temp;
	private int currentDay;
	private int currentMonth;
	
	/**
	 * Creates a calendar grid.
	 * @param theParentFrame the parent from for the component
	 */
	public CalendarGridComponent(CalendarGridFrame theParentFrame) {
		this.parentFrame = theParentFrame;
		this.temp = new GregorianCalendar();
		this.currentDay = temp.get(Calendar.DAY_OF_MONTH);
		this.currentMonth = temp.get(Calendar.MONTH);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Component c : this.getComponents()) {
			this.remove(c);
		}
		
		final int headerHeight = 40;
		
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight() - headerHeight;
		
		//-----HEADER
		final int numberOfDays = 7;
		double headerLabelWidth = frameWidth / numberOfDays;
		for (int i = 0; i < numberOfDays; i ++) {
			JLabel headerLabel = new JLabel(shortDays[i]);
			headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			headerLabel.setBounds(new Rectangle((int)headerLabelWidth*i, 0, (int)headerLabelWidth, headerHeight));
			this.add(headerLabel);
		}
		
		//------ DAYS
		int maxDaysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK);
		
		int numberOfRows = (5 + maxDaysInMonth + firstDayOfWeek) / 7;
		int rowHeight = frameHeight / numberOfRows;
		int columnWidth = frameWidth / 7;
		int currentDayOfWeek = firstDayOfWeek-1;
		int currentRow = 0;
		int month = temp.get(Calendar.MONTH);
		
		for (int i = 1; i <= maxDaysInMonth; i ++) {
			
			JButton dayButton = new JButton(""+i);
			dayButton.setSize(columnWidth, rowHeight);
			dayButton.setLocation(currentDayOfWeek*columnWidth, headerHeight + currentRow*rowHeight);
			dayButton.setOpaque(true);
			dayButton.setFocusPainted(false);
			if (currentDay == i && currentMonth == month) {
				dayButton.setBorder(new LineBorder(Color.BLACK));
			}
			int dayOffset = i - currentDay;
			dayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					temp.add(GregorianCalendar.DAY_OF_MONTH, dayOffset);
					parentFrame.datePressed(temp.getTime());
				}
			});
			this.add(dayButton);
			
			currentDayOfWeek ++;
			if (currentDayOfWeek == 7) {
				currentDayOfWeek = 0;
				currentRow ++;
			}
		}
		
	}
	
	/**
	 * Changes to the next or previous month.
	 * @param nextMonth true to change to next, false for previous
	 */
	public void changeMonth(boolean nextMonth) {
		if (nextMonth) {
			temp.add(GregorianCalendar.MONTH, 1);
		} else {
			temp.add(GregorianCalendar.MONTH, -1);
		}
		this.repaint();
		parentFrame.notifyChange();
	}
	
	/**
	 * Gets the current month of the grid.
	 * @return the current month of the grid
	 */
	public String getCurrentMonth() {
		return longMonths[temp.get(GregorianCalendar.MONTH)];
	}
	
}
