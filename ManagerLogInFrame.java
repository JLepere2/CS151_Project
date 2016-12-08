import javax.swing.*;
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

  private JPanel frameCenterPanel;

  public ManagerLogInFrame(final HotelReservationFrameManager frameManager, final HotelManager manager) {
    super(manager);

    JPanel headerPanel = new JPanel(new FlowLayout());
    frameCenterPanel = new JPanel();

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
    final CalendarGridComponent gridComp = new CalendarGridComponent(frameManager);
    frameCenterPanel.add(gridComp, BorderLayout.NORTH);
  }
}
