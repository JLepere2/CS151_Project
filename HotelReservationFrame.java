import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A Frame for the hotel reservation system.
 * @author JLepere2
 * Version 1.1
 */
public class HotelReservationFrame extends JFrame {

	private static final long serialVersionUID = 37263L;
	private static int FRAME_WIDTH = 500;
	private static int FRAME_HEIGHT = 500;
	private static String TITLE = "HOTEL RESERVATION SYSTEM";
	
	public HotelManager hotelManager;
	
	private JPanel frameNorthPanel;
	private JPanel frameCenterPanel;

	/**
	 * Creates a new HotelReservationFrame.
	 */
	public HotelReservationFrame(HotelManager theHotelManager) {
		
		this.hotelManager = theHotelManager;
		
		this.setTitle(TITLE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		
		this.frameNorthPanel = new JPanel();
		this.frameCenterPanel = new JPanel();
		
		this.add(frameNorthPanel, BorderLayout.NORTH);
		this.add(frameCenterPanel, BorderLayout.CENTER);
		
		showInitialView();
		
	}
	
	/**
	 * Shows the initial view of the frame.
	 */
	private void showInitialView() {
		
		frameNorthPanel.removeAll();
		frameCenterPanel.removeAll();
		
		frameNorthPanel.setLayout(new FlowLayout());
		
		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton guestButton = new JButton("GUEST");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGuestLogInView();
			}
		});
		
		frameNorthPanel.add(managerButton);
		frameNorthPanel.add(guestButton);
		
		validateAllRegions();
		
	}
	
	/**
	 * Shows the view for a guest log in / sign up.
	 */
	private void showGuestLogInView() {
		
		frameNorthPanel.removeAll();
		frameCenterPanel.removeAll();
		
		frameNorthPanel.setLayout(new FlowLayout());
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInitialView();
			}
		});
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSignUpView();
			}
		});
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSignInView();
			}
		});
		frameNorthPanel.add(backButton);
		frameNorthPanel.add(signUpButton);
		frameNorthPanel.add(signInButton);
		
		validateAllRegions();
		
	}
	
	/**
	 * Shows the signup view.
	 */
	private void showSignUpView() {
		
		frameCenterPanel.removeAll();
		frameCenterPanel.setLayout(new BoxLayout(frameCenterPanel, BoxLayout.Y_AXIS));
		
		final int textAreaHeight = 40;
		
		final JTextField firstNameTextField = new JTextField("First Name");
		firstNameTextField.setMaximumSize(new Dimension(frameCenterPanel.getWidth(), textAreaHeight));
		firstNameTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				firstNameTextField.setText("");
			}
		});
		final JTextField lastNameTextField = new JTextField("Last Name");
		lastNameTextField.setMaximumSize(new Dimension(frameCenterPanel.getWidth(), textAreaHeight));
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
				hotelManager.addGuestAccount(firstName, lastName);
				JOptionPane.showMessageDialog(null, firstName + " " + lastName + ", you have successfully signed up!\n"+""
												+ "Your ID number is: " + (GuestAccount.getCount()-1));
				showMainGuestView();
			}
		});
		
		frameCenterPanel.add(firstNameTextField);
		frameCenterPanel.add(lastNameTextField);
		frameCenterPanel.add(signUpButton);
		
		validateAllRegions();
		
	}
	
	/**
	 * The sign in view.
	 */
	public void showSignInView() {
		
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
					if (hotelManager.validIdForLogIn(idNumber)) {
						showMainGuestView();
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
		
		validateAllRegions();
		
	}
	
	/**
	 * Shows the main guest view after sign in.
	 */
	private void showMainGuestView() {
		
		frameNorthPanel.removeAll();
		frameCenterPanel.removeAll();
		
		frameNorthPanel.setLayout(new FlowLayout());
		
		JButton backButton = new JButton("Sign Out");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGuestLogInView();
			}
		});
		JButton makeReservationButton = new JButton("Make a Reservation");
		makeReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frameNorthPanel.add(backButton);
		frameNorthPanel.add(makeReservationButton);
		frameNorthPanel.add(viewOrCancelReservationButton);
		
		validateAllRegions();
	}
	
	/**
	 * Validates and repaints all regions.
	 */
	public void validateAllRegions() {
		frameNorthPanel.validate();
		frameCenterPanel.validate();
		frameNorthPanel.repaint();
		frameCenterPanel.repaint();
	}
	
}
