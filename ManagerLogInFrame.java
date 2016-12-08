import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rodion on 12/6/16.
 */
public class ManagerLogInFrame extends HotelReservationFrame {

  private CalendarGridComponent gridComp;
  private JPanel frameCenterPanel;

  public ManagerLogInFrame(final HotelReservationFrameManager frameManager, final HotelManager manager) {
    super(manager);

    JPanel headerPanel = new JPanel(new FlowLayout());
    frameCenterPanel = new JPanel(new GridLayout(1,2));
    frameCenterPanel.setBorder(new EmptyBorder(20, 20, 90 ,20));
    
    JButton loadButton = new JButton("Load Reservations");
    loadButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
    });

    JButton viewButton = new JButton("View Reservations");
    viewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        managerMonthView(frameManager);
      }
    });

    JButton backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frameManager.showFrame(new UserSelectionFrame(frameManager, manager));
      }
    });

    headerPanel.add(loadButton);
    headerPanel.add(viewButton);
    headerPanel.add(backButton);
    this.add(headerPanel, BorderLayout.NORTH);
    this.add(frameCenterPanel, BorderLayout.CENTER);
  }


  public void managerMonthView(HotelReservationFrameManager frameManager) {
    gridComp = new CalendarGridComponent(frameManager);
    JTextArea roomAvailabilityArea = new JTextArea("Room Information: ");
    frameCenterPanel.add(gridComp);
    frameCenterPanel.add(roomAvailabilityArea);
    frameCenterPanel.validate();
    frameCenterPanel.repaint();
  }
}
