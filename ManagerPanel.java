import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerPanel extends JPanel {

  private static final long serialVersionUID = 1531L;
  private static String identifier = "ManagerPanel";

  public ManagerPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

    this.setLayout(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout());
    final ManagerViewPanel managerView = new ManagerViewPanel(mainCardPanel, hotelManager);
    managerView.setVisible(false);

    JButton loadViewButton = new JButton("Load/View Reservations");
    loadViewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        managerView.setVisible(true);
      }
    });

    JButton backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, UserSelectionPanel.getStateIdentifier());
      }
    });

    headerPanel.add(loadViewButton);
    headerPanel.add(backButton);
    this.add(headerPanel, BorderLayout.NORTH);
    this.add(managerView, BorderLayout.CENTER);

  }

  public static String getStateIdentifier() {
    return identifier;
  }
}
