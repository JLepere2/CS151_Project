import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuestLogInFrame extends HotelReservationFrame {

	private static final long serialVersionUID = 1L;
	private JPanel frameCenterPanel;
	
	public GuestLogInFrame(HotelReservationFrameManager frameManager, HotelManager hotelManager) {
		super(hotelManager);

		this.frameCenterPanel = new JPanel();
		
		JPanel headerPanel = new JPanel(new FlowLayout());

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameManager.showFrame(new UserSelectionFrame(frameManager, hotelManager));
			}
		});
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSignUpView(frameManager);
			}
		});
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSignInView(frameManager);
			}
		});
		headerPanel.add(backButton);
		headerPanel.add(signUpButton);
		headerPanel.add(signInButton);
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(frameCenterPanel, BorderLayout.CENTER);
		
	}

	private void showSignUpView(HotelReservationFrameManager frameManager) {
		
		frameCenterPanel.removeAll();
		frameCenterPanel.setLayout(new BoxLayout(frameCenterPanel, BoxLayout.Y_AXIS));
		
		final int textAreaHeight = 40;

		final JTextField firstNameTextField = new JTextField("First Name");
		firstNameTextField.setMaximumSize(new Dimension(this.getWidth(), textAreaHeight));
		firstNameTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				firstNameTextField.setText("");
			}
		});
		final JTextField lastNameTextField = new JTextField("Last Name");
		lastNameTextField.setMaximumSize(new Dimension(this.getWidth(), textAreaHeight));
		lastNameTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lastNameTextField.setText("");
			}
		});
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				getManager().addGuestAccount(firstName, lastName);
				JOptionPane.showMessageDialog(null, firstName + " " + lastName + ", you have successfully signed up!\n"
						+ "" + "Your ID number is: " + (GuestAccount.getCount()));
				frameManager.showFrame(new MainGuestViewFrame(frameManager, getManager()));
			}
		});
		
		frameCenterPanel.add(firstNameTextField);
		frameCenterPanel.add(lastNameTextField);
		frameCenterPanel.add(signUpButton);

		frameCenterPanel.validate();
		frameCenterPanel.repaint();

	}
	
	/**
	 * The sign in view.
	 */
	public void showSignInView(HotelReservationFrameManager frameManager) {

		frameCenterPanel.removeAll();
		frameCenterPanel.setLayout(new BoxLayout(frameCenterPanel, BoxLayout.Y_AXIS));

		final int textAreaHeight = 40;

		final JTextField userIdTextField = new JTextField("Account Id");
		userIdTextField.setMaximumSize(new Dimension(frameCenterPanel.getWidth(), textAreaHeight));
		userIdTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				userIdTextField.setText("");
			}
		});
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int idNumber = Integer.parseInt(userIdTextField.getText());
					if (getManager().validIdForLogIn(idNumber)) {
						frameManager.showFrame(new MainGuestViewFrame(frameManager, getManager()));
					} else {
						JOptionPane.showMessageDialog(null, "Invalid ID");
					}

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid ID");
				}
			}
		});

		frameCenterPanel.add(userIdTextField);
		frameCenterPanel.add(signInButton);

		frameCenterPanel.validate();
		frameCenterPanel.repaint();

	}
	
}
