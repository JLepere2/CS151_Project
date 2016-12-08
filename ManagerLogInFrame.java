import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    loadButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ManagerMonthView();
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


  public void ManagerMonthView() {
    frameCenterPanel.removeAll();
    final JTextField dateNow = new JTextField(CalendarGridFrame.getDateString(new Date()));
    CalendarGridFrame frame = new CalendarGridFrame(dateNow);
    CalendarGridComponent gridFrame = new CalendarGridComponent(frame);
    gridFrame.setVisible(true);

    frameCenterPanel.add(gridFrame);
    this.add(frameCenterPanel, BorderLayout.CENTER);

  }
}
