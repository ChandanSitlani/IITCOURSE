package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.LoginInfo;
import models.UserInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8120031627944781317L;
	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JPasswordField pwdPassword;
	private OnloginSuccess listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public LoginDialog(Object obj) {
		setModal(true);
		setTitle("Login");
		if(obj!=null)
			listener=(OnloginSuccess)obj;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel("UserName");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 0;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			username = new JTextField();
			GridBagConstraints gbc_username = new GridBagConstraints();
			gbc_username.insets = new Insets(0, 0, 5, 0);
			gbc_username.fill = GridBagConstraints.HORIZONTAL;
			gbc_username.gridx = 1;
			gbc_username.gridy = 1;
			contentPanel.add(username, gbc_username);
			username.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 2;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			pwdPassword = new JPasswordField();
			pwdPassword.setText("");
			GridBagConstraints gbc_pwdPassword = new GridBagConstraints();
			gbc_pwdPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPassword.gridx = 1;
			gbc_pwdPassword.gridy = 3;
			contentPanel.add(pwdPassword, gbc_pwdPassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						login();
						LoginDialog.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						LoginDialog.this.dispose();
					}
				});
			}
		}
	}
	
	public void login(){
		String uname = username.getText();
		String upass = String.valueOf(pwdPassword.getPassword());
		UserInfo userInfo = DataStorage.dStorage.helper.getUserInfo(new LoginInfo(uname, upass));
		DataStorage.dStorage.userInfo=userInfo;
		if(userInfo!=null){
			login_success();
			listener.logined();
		}
		else
			login_filed();
				
	}
	private void login_filed(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(LoginDialog.this,
				"Login failed",
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,options,options[0]);
	}
	private void login_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(LoginDialog.this,
				"loing success",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
		
	
    public interface OnloginSuccess {
        public void logined();
    }

}
