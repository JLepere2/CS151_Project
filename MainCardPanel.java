import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainCardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GuestAccount currentAccount;
	
	public MainCardPanel() {
		this.setLayout(new CardLayout());
	}
	
	public void setCurrentAccount(GuestAccount theGuestAccount) {
		System.out.println("SET");
		currentAccount = theGuestAccount;
	}
	
	public GuestAccount getCurrentAccount() {
		System.out.println("GET");
		return currentAccount;
	}
	
}
