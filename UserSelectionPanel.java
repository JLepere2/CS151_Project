import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1256416L;
	private static String identifier = "UserSelectionPanel";
	
	public UserSelectionPanel(final MainCardPanel mainCardPanel) {
		this.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel(new FlowLayout());
		
		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, ManagerPanel.getStateIdentifier());
			}
		});
		JButton guestButton = new JButton("GUEST");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
			}
		});
		
		headerPanel.add(managerButton);
		headerPanel.add(guestButton);
		this.add(headerPanel, BorderLayout.NORTH);
	}

	public static String getStateIdentifier() {
		return identifier;
	}
	
}
