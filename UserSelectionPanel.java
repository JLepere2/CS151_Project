import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1256416L;
	private static String identifier = "UserSelectionPanel";
	
	public UserSelectionPanel(final MainCardPanel mainCardPanel) {
		this.setLayout(new FlowLayout());
		JPanel headerPanel = new JPanel(new GridLayout(1,2,100,50));
		headerPanel.setBorder(new EmptyBorder(200, 0, 100, 0));
		
		JButton managerButton = new JButton("MANAGER");
		managerButton.setPreferredSize(new Dimension(200, 100));
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, ManagerPanel.getStateIdentifier());
			}
		});
		JButton guestButton = new JButton("GUEST");
		guestButton.setPreferredSize(new Dimension(200, 100));
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
			}
		});
		
		headerPanel.add(managerButton);
		headerPanel.add(guestButton);
		this.add(headerPanel, BorderLayout.CENTER);
	}

	public static String getStateIdentifier() {
		return identifier;
	}
	
}
