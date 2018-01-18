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
import models.Address;
import models.UserInfo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddressDetailsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5612783674604630855L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtStreet;
	private JTextField txtDept;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZipcode;
	private JTextField txtCountry;
	private Address address;
	private OnAddressUpdate listener;
	private UserInfo userInfo;
	private DataHelper helper;
	private JTextField txtPhone;

	/**
	 * Launch the application.
	 */
	public AddressDetailsDialog(Object obj,Address address) {
		setBounds(100, 100, 456, 373);
		setModal(true);
		if(obj!=null)
			listener=(OnAddressUpdate)obj;
		this.address = address;
		userInfo=DataStorage.dStorage.userInfo;
		helper=DataStorage.dStorage.helper;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		{
			JLabel lblStreet = new JLabel("Street");
			contentPanel.add(lblStreet, "cell 0 0,alignx trailing");
		}
		{
			txtStreet = new JTextField();
			txtStreet.setText("");
			contentPanel.add(txtStreet, "cell 1 0,growx");
			txtStreet.setColumns(10);
		}
		{
			JLabel lblDeptnum = new JLabel("dept_num");
			contentPanel.add(lblDeptnum, "cell 0 1,alignx trailing");
		}
		{
			txtDept = new JTextField();
			txtDept.setText("");
			contentPanel.add(txtDept, "cell 1 1,growx");
			txtDept.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("city");
			contentPanel.add(lblCity, "cell 0 2,alignx trailing");
		}
		{
			txtCity = new JTextField();
			txtCity.setText("");
			contentPanel.add(txtCity, "cell 1 2,growx");
			txtCity.setColumns(10);
		}
		{
			JLabel lblState = new JLabel("State");
			contentPanel.add(lblState, "cell 0 3,alignx trailing");
		}
		{
			txtState = new JTextField();
			txtState.setText("");
			contentPanel.add(txtState, "cell 1 3,growx");
			txtState.setColumns(10);
		}
		{
			JLabel lblZipcode = new JLabel("zipcode");
			contentPanel.add(lblZipcode, "cell 0 4,alignx trailing");
		}
		{
			txtZipcode = new JTextField();
			txtZipcode.setText("");
			contentPanel.add(txtZipcode, "cell 1 4,growx");
			txtZipcode.setColumns(10);
		}
		{
			JLabel lblCountry = new JLabel("country");
			contentPanel.add(lblCountry, "cell 0 5,alignx trailing");
		}
		{
			txtCountry = new JTextField();
			txtCountry.setText("");
			contentPanel.add(txtCountry, "cell 1 5,growx");
			txtCountry.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("delete");
				btnDelete.setVisible(address!=null);
				btnDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						delAddress();
					}
				});
				buttonPane.add(btnDelete);
			}
			{
				JButton okButton = new JButton("save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						doAddress();
					}
				});
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
						AddressDetailsDialog.this.dispose();
					}
				});
			}
		}
		{
			JLabel lblPhone = new JLabel("phone");
			contentPanel.add(lblPhone, "cell 0 6,alignx trailing");
		}
		{
			txtPhone = new JTextField();
			txtPhone.setText("");
			contentPanel.add(txtPhone, "cell 1 6,growx");
			txtPhone.setColumns(10);
		}
		if(address==null){
			return;
		}
		txtCity.setText(address.getCity());
		txtCountry.setText(address.getCountry());
		txtDept.setText(address.getDeptNum());
		txtState.setText(address.getState());
		txtStreet.setText(address.getStreet());
		txtPhone.setText(address.getPhoneNumber());
		txtZipcode.setText(address.getZipCode());
		
	}
	private void change_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(AddressDetailsDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void change_success(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(AddressDetailsDialog.this,
				info,
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	
	private void delAddress(){
		if(helper.deleteAddress(userInfo.getUid(), address.getAddrID())){
			change_success("address ideleted");
			listener.addressrefresh();
			this.dispose();
			return;
		}else{
			change_filed("can not deleted address,make sure this address are not using");
			return;
		}
	}
	
	private void doAddress(){
		String ci=txtCity.getText().trim();
		String co=txtCountry.getText().trim();
		String de=txtDept.getText().trim();
		String sa=txtState.getText().trim();
		String st=txtStreet.getText().trim();
		String ph=txtPhone.getText().trim();
		String zi=txtZipcode.getText().trim();
		if(ci.length()==0||co.length()==0||de.length()==0||st.length()==0||ph.length()==0||zi.length()==0){
			change_filed("missing information");
			return;
		}
		
		Address ad;
		if(address == null){
			ad=new Address(st, de, ci, sa, zi, co, ph);
			if(helper.insertAddress(ad, userInfo.getUid())){
				change_success("new address saved");
				listener.addressrefresh();
				this.dispose();
				return;
			}else{
				change_filed("can not save address");
				return;
			}
		}
		else{
			ad = address;
			ad.setCity(ci);
			ad.setCountry(co);
			ad.setDeptNum(de);
			ad.setPhoneNumber(ph);
			ad.setState(sa.length()==0?null:sa);
			ad.setStreet(st);
			ad.setZipCode(zi);
			if(helper.updateAddress(ad, userInfo.getUid())){
				change_success("address infomation updated");
				listener.addressrefresh();
				this.dispose();
				return;
			}else{
				change_filed("can not save address");
				return;
			}
		}
		
	}
	
	
	public interface OnAddressUpdate{
		public void addressrefresh();
	}

}
