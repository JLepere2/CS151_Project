import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rodion on 12/7/16.
 */
public class ManagerViewPanel extends JPanel {

  private static String identifier = "ManagerViewPanel";
  private static final long serialVersionUID = 1215316L;
  private ArrayList<HotelRoom> availableRoomsEconomic;
  private ArrayList<HotelRoom> availableRoomsLuxurious;



  public ManagerViewPanel(MainCardPanel mainCardPanel, final DateRangeReservationModel dateRangeModel, final HotelManager hotelManager) {
    CalendarGridComponent calendarGrid = new CalendarGridComponent(this, dateRangeModel);
    ArrayList<Reservation> reservationQueue = new ArrayList<>();
    availableRoomsEconomic = dateRangeModel.getReservationAvailable(reservationQueue);
    dateRangeModel.setRoomSelected(false);
    availableRoomsLuxurious = dateRangeModel.getReservationAvailable(reservationQueue);

    JTextArea roomAvailabilityArea = new JTextArea("Room Availability: " + "\n\n"
        + "Economic Rooms Left: " + availableRoomsEconomic.size() + "\n\n"
        + "Luxurious Rooms Left: " + availableRoomsLuxurious.size());
    final JTextArea roomInformation = new JTextArea("Information about the room: ");

    this.setLayout(new GridLayout(2,2,20,20));
    this.setBorder(new EmptyBorder(20,20,100,20));

    JPanel roomView = new JPanel(new GridLayout(4,1));
    JLabel economicRoomInfo = new JLabel("Economic Room Information: ");
    JPanel economicRoomPanel = new JPanel(new GridLayout(2,5,10,10));
    JLabel luxuriousRoomInfo = new JLabel("Luxurious Room Information: ");
    JPanel luxuriousRoomPanel = new JPanel(new GridLayout(2,5,10,10));

    for (int i = 1; i <= 10; i++){
      JButton button = new JButton(i + "");
      button.setForeground(Color.red);
      for (HotelRoom room : availableRoomsEconomic ) {
        if (room.getRoomNumber() == i)
          button.setForeground(Color.green);
      }
      economicRoomPanel.add(button);
    }

    for (int i = 1; i <= 10; i++){
      JButton button = new JButton(i + "");
      button.setForeground(Color.red);
      for (HotelRoom room : availableRoomsLuxurious ) {
        if (room.getRoomNumber() == i) {
          button.setForeground(Color.green);
          button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              roomInformation.setText("");
              roomInformation.setText("Room is available");
            }
          });
        }
      }
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          roomInformation.setText("Room is occupied");
        }
      });
      luxuriousRoomPanel.add(button);
    }
    roomInformation.repaint();

    roomView.add(economicRoomInfo);
    roomView.add(economicRoomPanel);
    roomView.add(luxuriousRoomInfo);
    roomView.add(luxuriousRoomPanel);

    this.add(calendarGrid);
    this.add(roomAvailabilityArea);
    this.add(roomView);
    this.add(roomInformation);
  }

  public static String getStateIdentifier() {
    return identifier;
  }

}



//    String availabilityText = dateRangeModel.getDateRangeHeader();
//    for (HotelRoom r : availableRooms) {
//      availabilityText += r.getRoomNumber() + "\n";
//    }
//    roomInformation.setText(availabilityText);
//    roomInformation.repaint();
//
//    dateRangeModel.setRoomSelected(false);
//    availableRooms = dateRangeModel.getReservationAvailable(reservationQueue);
//    availabilityText = dateRangeModel.getDateRangeHeader();
//    for (HotelRoom r : availableRooms) {
//      availabilityText += r.getRoomNumber() + "\n";
//    }

//    roomInformation.setText(availabilityText);