package airline;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2795790104406565862L;
	private JFrame par;
	/**
	 * Create the panel.
	 */
	public LoginPanel(JFrame frame) {
		setBounds(00, 00, 400, 1000);
		setLayout(null);
		par=frame;
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginDialog dialog =new LoginDialog(par);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnLogin.setBounds(50, 321, 480, 40);
		add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(50, 436, 480, 40);
		add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegDialog dialog =new RegDialog(par);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JLabel lblYouAreNot = new JLabel("You are not login, please Login first");
		lblYouAreNot.setBounds(0, 0, 600, 21);
		add(lblYouAreNot);

	}
	
}
