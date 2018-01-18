package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import models.LoginInfo;
import models.UserInfo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class RegDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1813184941357440471L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMailAddress;
	private JPasswordField pwdPassword;
	private JPasswordField pwdReplacepassword;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private OnRegSuccess onRegSuccess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegDialog dialog = new RegDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegDialog(Object obj) {
		if(obj != null)
			onRegSuccess = (OnRegSuccess) obj;
		setModal(true);
		setTitle("register");
		setBounds(100, 100, 449, 553);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel("Username");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 1;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			txtFirstName = new JTextField();
			txtFirstName.setText("");
			GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
			gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
			gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFirstName.gridx = 1;
			gbc_txtFirstName.gridy = 1;
			contentPanel.add(txtFirstName, gbc_txtFirstName);
			txtFirstName.setColumns(10);
		}
		{
			txtMiddleName = new JTextField();
			txtMiddleName.setText("");
			GridBagConstraints gbc_txtMiddleName = new GridBagConstraints();
			gbc_txtMiddleName.insets = new Insets(0, 0, 5, 0);
			gbc_txtMiddleName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMiddleName.gridx = 1;
			gbc_txtMiddleName.gridy = 2;
			contentPanel.add(txtMiddleName, gbc_txtMiddleName);
			txtMiddleName.setColumns(10);
		}
		{
			txtLastName = new JTextField();
			txtLastName.setText("");
			GridBagConstraints gbc_txtLastName = new GridBagConstraints();
			gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
			gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLastName.gridx = 1;
			gbc_txtLastName.gridy = 3;
			contentPanel.add(txtLastName, gbc_txtLastName);
			txtLastName.setColumns(10);
		}
		{
			JLabel lblEmailAddress = new JLabel("Email address");
			GridBagConstraints gbc_lblEmailAddress = new GridBagConstraints();
			gbc_lblEmailAddress.anchor = GridBagConstraints.EAST;
			gbc_lblEmailAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmailAddress.gridx = 0;
			gbc_lblEmailAddress.gridy = 5;
			contentPanel.add(lblEmailAddress, gbc_lblEmailAddress);
		}
		{
			txtMailAddress = new JTextField();
			txtMailAddress.setText("");
			GridBagConstraints gbc_txtMailAddress = new GridBagConstraints();
			gbc_txtMailAddress.insets = new Insets(0, 0, 5, 0);
			gbc_txtMailAddress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMailAddress.gridx = 1;
			gbc_txtMailAddress.gridy = 5;
			contentPanel.add(txtMailAddress, gbc_txtMailAddress);
			txtMailAddress.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("password");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 6;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			pwdPassword = new JPasswordField();
			pwdPassword.setText("");
			GridBagConstraints gbc_pwdPassword = new GridBagConstraints();
			gbc_pwdPassword.insets = new Insets(0, 0, 5, 0);
			gbc_pwdPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPassword.gridx = 1;
			gbc_pwdPassword.gridy = 6;
			contentPanel.add(pwdPassword, gbc_pwdPassword);
		}
		{
			JLabel lblReplyPassword = new JLabel("reply password");
			GridBagConstraints gbc_lblReplyPassword = new GridBagConstraints();
			gbc_lblReplyPassword.anchor = GridBagConstraints.EAST;
			gbc_lblReplyPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblReplyPassword.gridx = 0;
			gbc_lblReplyPassword.gridy = 7;
			contentPanel.add(lblReplyPassword, gbc_lblReplyPassword);
		}
		{
			pwdReplacepassword = new JPasswordField();
			pwdReplacepassword.setText("");
			GridBagConstraints gbc_pwdReplacepassword = new GridBagConstraints();
			gbc_pwdReplacepassword.insets = new Insets(0, 0, 5, 0);
			gbc_pwdReplacepassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdReplacepassword.gridx = 1;
			gbc_pwdReplacepassword.gridy = 7;
			contentPanel.add(pwdReplacepassword, gbc_pwdReplacepassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("register");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						tryreg();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						RegDialog.this.dispose();
					}
				});
			}
		}
	}
	
	public void tryreg(){
		DataHelper helper = DataStorage.dStorage.helper;
		String email = txtMailAddress.getText().trim();
		String pass = String.valueOf(pwdPassword.getPassword());
		LoginInfo loginInfo = new LoginInfo(email, pass);
		if(!helper.isEmailAddrFree(loginInfo)){
			reg_filed("Email address already be used");
			return;
		}
		String fname = txtFirstName.getText().trim();
		String mname = txtMiddleName.getText().trim();
		String lname = txtLastName.getText().trim();
		if(fname == null|| lname ==null){
			reg_filed("information  incomplete");
			return;
		}
		if(mname.length()==0)
			mname=null;
		String rp = String.valueOf(pwdReplacepassword.getPassword());
		if(!rp.equals(pass)||pass.length()==0){
			reg_filed("please check password");
			return;
		}
		UserInfo userInfo = new UserInfo(fname, mname, lname, null); 
		if(!helper.register(loginInfo, userInfo)){
			reg_filed("reg filed");
			return;
		}
		userInfo = helper.getUserInfo(loginInfo);
		if(userInfo==null){
			reg_filed("reg filed!");
			return;
		}
		DataStorage.dStorage.userInfo = userInfo;
		onRegSuccess.reged();
		reg_success();
		RegDialog.this.dispose();
		
	}
	
	private void reg_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(RegDialog.this,
				info,
				"error",
				JOptionPane.YES_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,options,options[0]);
	}
	
	private void reg_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(RegDialog.this,
				"register success",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
    public interface OnRegSuccess {
        public void reged();
    }
}
