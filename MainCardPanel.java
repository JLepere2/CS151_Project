import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainCardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GuestAccount currentAccount;
	
	public MainCardPanel() {
		this.setLayout(new CardLayout());
	}
	
	public void setCurrentAccount(GuestAccount theGuestAccount) {
		currentAccount = theGuestAccount;
	}
	
	public GuestAccount getCurrentAccount() {
		return currentAccount;
	}
	
}
