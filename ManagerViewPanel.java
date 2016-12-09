import javax.swing.*;
import java.awt.*;

/**
 * Created by Rodion on 12/7/16.
 */
public class ManagerViewPanel extends JPanel {

  private static String identifier = "ManagerViewPanel";
  private static final long serialVersionUID = 1215316L;

  public ManagerViewPanel(MainCardPanel mainCardPanel, HotelManager hotelManager) {

    this.setLayout(new BorderLayout());
    JButton testingButton = new JButton("asdasd");
    //----CENTER PANEL ---//
    this.add(testingButton);
  }

  public static String getStateIdentifier() {
    return identifier;
  }

}
