package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import models.UserInfo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ChangePasswordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756687684804809714L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldpwd;
	private JPasswordField newpwd;
	private JPasswordField compwd;
	private DataHelper helper;
	private UserInfo userInfo;

	public ChangePasswordDialog() {
		setModal(true);
		helper = DataStorage.dStorage.helper;
		userInfo = DataStorage.dStorage.userInfo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		{
			JLabel lblOldpassword = new JLabel("Old Password");
			contentPanel.add(lblOldpassword, "cell 0 0,alignx trailing");
		}
		{
			oldpwd = new JPasswordField();
			contentPanel.add(oldpwd, "cell 1 0,growx");
		}
		{
			JLabel lblNewPassword = new JLabel("New Password");
			contentPanel.add(lblNewPassword, "cell 0 1,alignx trailing");
		}
		{
			newpwd = new JPasswordField();
			contentPanel.add(newpwd, "cell 1 1,growx");
		}
		{
			JLabel lblConform = new JLabel("Conform");
			contentPanel.add(lblConform, "cell 0 2,alignx trailing");
		}
		{
			compwd = new JPasswordField();
			contentPanel.add(compwd, "cell 1 2,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						changePassword();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						ChangePasswordDialog.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	private void change_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(ChangePasswordDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void change_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(ChangePasswordDialog.this,
				"password changed",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	
	private void changePassword(){
		String o = String.valueOf(oldpwd.getPassword());
		String n = String.valueOf(newpwd.getPassword());
		String c = String.valueOf(compwd.getPassword());
		if(o.length()==0){
			change_filed("please input old password!");
			return;
		}
		if(!n.equals(c)){
			change_filed("new possword different from conform!");
			return;
		}
		if(!helper.updateLoginInfo(c, o, userInfo.getUid())){
			change_filed("old password wrong");
			return;
		}
		change_success();
		this.dispose();
	}

}
