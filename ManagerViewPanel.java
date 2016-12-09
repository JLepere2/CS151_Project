import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Rodion on 12/7/16.
 */
public class ManagerViewPanel extends JPanel {

  private static String identifier = "ManagerViewPanel";
  private static final long serialVersionUID = 1215316L;

  public ManagerViewPanel(MainCardPanel mainCardPanel, HotelManager hotelManager) {
    CalendarGridComponent calendarGrid = new CalendarGridComponent(this);
    JTextArea roomAvailabilityArea = new JTextArea("Room Availability: " + "\n\n" + "Economic Rooms Left: " + "\n\n"
        + "Luxurious Rooms Left: ");
    JLabel roomView = new JLabel("Room View Design will go here.");
    JTextArea roomInformation = new JTextArea("Information about the room: ");

    this.setLayout(new GridLayout(2,2,20,20));
    this.setBorder(new EmptyBorder(20, 20, 100, 20));

    this.add(calendarGrid);
    this.add(roomAvailabilityArea);
    this.add(roomView);
    this.add(roomInformation);
  }

  public static String getStateIdentifier() {
    return identifier;
  }

}
