package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import models.UserInfo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class UpdateUserInfoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2418908276629694978L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstname;
	private JTextField txtMiddlename;
	private JTextField txtLastname;
	private UpdateUserInfoSuccess listener;
	private DataHelper helper;
	private UserInfo userInfo;
	private JComboBox<String> comboBox;
	String[] iata; 

	public UpdateUserInfoDialog(Object obj,UserInfo userInfo) {
		if(obj!=null)
			listener = (UpdateUserInfoSuccess)obj;
		setModal(true);
		this.userInfo=userInfo;
		helper = DataStorage.dStorage.helper;
		ArrayList<String> iatalist = helper.iatas();
		iata = new String[iatalist.size()];
		iatalist.toArray(iata);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		{
			JLabel lblFirstName = new JLabel("First Name");
			contentPanel.add(lblFirstName, "cell 0 0,alignx trailing");
		}
		{
			txtFirstname = new JTextField();
			txtFirstname.setText(userInfo.getFirstName());
			contentPanel.add(txtFirstname, "cell 1 0,growx");
			txtFirstname.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name");
			contentPanel.add(lblMiddleName, "cell 0 1,alignx trailing");
		}
		{
			txtMiddlename = new JTextField();
			txtMiddlename.setText(userInfo.getMiddleName());
			contentPanel.add(txtMiddlename, "cell 1 1,growx");
			txtMiddlename.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			contentPanel.add(lblLastName, "cell 0 2,alignx trailing");
		}
		{
			txtLastname = new JTextField();
			txtLastname.setText(userInfo.getLastName());
			contentPanel.add(txtLastname, "cell 1 2,growx");
			txtLastname.setColumns(10);
		}
		{
			JLabel lblHomeAirport = new JLabel("Home Airport");
			contentPanel.add(lblHomeAirport, "cell 0 3,alignx trailing");
		}
		{
			comboBox = new JComboBox<String>(iata);
			if(userInfo.getHomeIATA().equals("UDF"))
				comboBox.setSelectedIndex(-1);
			else
				comboBox.setSelectedItem(userInfo.getHomeIATA());
			contentPanel.add(comboBox, "cell 1 3,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						update();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						UpdateUserInfoDialog.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void change_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(UpdateUserInfoDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void change_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(UpdateUserInfoDialog.this,
				"user infomation updated",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	private void update(){
		String fname = txtFirstname.getText().trim();
		String mname = txtMiddlename.getText().trim();
		String lname = txtLastname.getText().trim();
		String iata;
		try{
			iata = comboBox.getSelectedItem().toString();
		}catch (NullPointerException e) {
			// TODO: handle exception
			change_filed("pleast select Home Airport");
			return;
		}
		if(fname.length()==0||lname.length()==0){
			change_filed("first name and last name cannot be empty");
			return;
		}
		userInfo.setFirstName(fname);
		userInfo.setMiddleName(mname.length()==0?null:mname);
		userInfo.setLastName(lname);
		userInfo.setHomeIATA(iata);
		if(helper.updateUserInfo(userInfo)){
			change_success();
			this.dispose();
			listener.onUpdateUinfoSuccess();
			return;
		}
		change_filed("can not updata datapase");
		return;
	}
	
	public interface UpdateUserInfoSuccess{
		public void onUpdateUinfoSuccess();
	}

}
