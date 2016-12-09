import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainGuestViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1215316L;
	private static String identifier = "MainGuestViewPanel";
	
	public MainGuestViewPanel(MainCardPanel mainCardPanel, HotelManager hotelManager) {
		
		this.setLayout(new BorderLayout());
		
		final DateRangeReservationModel dateRangeModel = new DateRangeReservationModel(hotelManager); 
		
		//----CENTER PANEL ---//
		JPanel centerPanel = new JPanel(new BorderLayout());
		MainGuestAvailabilityPanel availabilityPanel = new MainGuestAvailabilityPanel(mainCardPanel, dateRangeModel, hotelManager);
		MainGuestSelectionPanel selectionPanel = new MainGuestSelectionPanel(dateRangeModel, availabilityPanel);
		selectionPanel.setVisible(false);
		availabilityPanel.setVisible(false);
		centerPanel.add(selectionPanel, BorderLayout.NORTH);
		centerPanel.add(availabilityPanel, BorderLayout.CENTER);
		
		//----HEADER----//
		MainGuestHeaderPanel headerPanel = new MainGuestHeaderPanel(mainCardPanel, hotelManager, selectionPanel, availabilityPanel);
		
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
	}
	
	public static String getStateIdentifier() {
		return identifier;
	}
}
