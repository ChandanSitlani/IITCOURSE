package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import DataBase.DataHelper;
import models.Address;
import models.CreditCard;
import models.UserInfo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CardDetailsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6594012434483875500L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCard;
	private JTextField txtHolder;
	private JTextField txtMm;
	private JTextField txtYy;
	private JTextField txtCVV;
	private JComboBox<Integer> comboBox;
	private DataHelper helper;
	private CreditCard cCard;
	private OnCardUpdate listener;
	private UserInfo userInfo;

	public CardDetailsDialog(Object obj, CreditCard card,ArrayList<Address> list) {
		setModal(true);
		if(obj!=null)
			listener = (OnCardUpdate) obj;
		cCard=card;
		helper = DataStorage.dStorage.helper;
		userInfo = DataStorage.dStorage.userInfo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][center][grow]", "[][][][][]"));
		{
			JLabel lblCardNumber = new JLabel("card number");
			contentPanel.add(lblCardNumber, "cell 0 0,alignx trailing");
		}
		{
			txtCard = new JTextField();
			txtCard.setText("");
			contentPanel.add(txtCard, "cell 1 0 3 1,growx");
			txtCard.setColumns(10);
		}
		{
			JLabel lblHolder = new JLabel("holder");
			contentPanel.add(lblHolder, "cell 0 1,alignx trailing");
		}
		{
			txtHolder = new JTextField();
			txtHolder.setText("");
			contentPanel.add(txtHolder, "cell 1 1 3 1,growx");
			txtHolder.setColumns(10);
		}
		{
			JLabel lblExpiration = new JLabel("expiration");
			contentPanel.add(lblExpiration, "cell 0 2,alignx trailing");
		}
		{
			txtMm = new JTextField();
			txtMm.setText("");
			contentPanel.add(txtMm, "cell 1 2,growx");
			txtMm.setColumns(10);
		}
		{
			JLabel label = new JLabel("/");
			contentPanel.add(label, "cell 2 2,alignx trailing");
		}
		{
			txtYy = new JTextField();
			txtYy.setText("");
			contentPanel.add(txtYy, "cell 3 2,growx");
			txtYy.setColumns(10);
		}
		{
			JLabel lblCvv = new JLabel("cvv");
			contentPanel.add(lblCvv, "cell 0 3,alignx trailing");
		}
		{
			txtCVV = new JTextField();
			contentPanel.add(txtCVV, "cell 1 3 3 1,growx");
			txtCVV.setColumns(10);
		}
		{
			JLabel lblBillingAddress = new JLabel("billing address");
			contentPanel.add(lblBillingAddress, "cell 0 4,alignx trailing");
		}
		{
			JLabel lblAddressId = new JLabel("address id");
			contentPanel.add(lblAddressId, "cell 1 4,alignx right");
		}
		{
			Integer[] ids = new Integer[list.size()];
			for(int i=0;i<ids.length;i++){
				ids[i]=list.get(i).getAddrID();
			}
			comboBox = new JComboBox<Integer>(ids);
			comboBox.setSelectedIndex(-1);
			contentPanel.add(comboBox, "cell 3 4,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("Delete");
				buttonPane.add(btnDelete);
				btnDelete.setVisible(cCard!=null);
				btnDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						delCard();
					}
				});
			}
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						doCard();
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
						CardDetailsDialog.this.dispose();
					}
				});
			}
		}
		if(cCard==null)
			return;
		txtCard.setText(cCard.getCardNumber());
		txtHolder.setText(cCard.getCardHolder());
		txtCVV.setText(String.format("%03d", cCard.getCvv()));
		int ext = cCard.getExpirationDate();
		txtMm.setText(String.format("%02d", ext/100));
		txtYy.setText(String.format("%02d", ext%100));
		comboBox.setSelectedItem(cCard.getBilliing_address());
	}
	
	private void change_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(CardDetailsDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void change_success(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(CardDetailsDialog.this,
				info,
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	
	private void doCard(){
		String cn=txtCard.getText().trim();
		String na=txtHolder.getText().trim();
		String cvv = txtCVV.getText().trim();
		String yy = txtYy.getText().trim();
		String mm = txtMm.getText().trim();
		Integer aid = (Integer)comboBox.getSelectedItem();
		if(!isNumeric(cn)){
			change_filed("card number must be numeric");
			return;
		}
		if(na.length()==0||cvv.length()==0||yy.length()==0||mm.length()==0||cn.length()==0||aid==null||cn.length()<5){
			change_filed("missing information");
			return;
		}
		int cvvv, yyy,mmm,exp;
		try{
			yyy=Integer.parseInt(yy);
			mmm=Integer.parseInt(mm);
			cvvv=Integer.parseInt(cvv);
			if(yyy<=0||mmm>12||mmm<=0||yyy>99||cvvv>999||cvvv<=0){
				change_filed("number error!");
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			change_filed("number error");
			return;
		}
		exp=100*mmm+yyy;
		CreditCard card = new CreditCard(userInfo.getUid(), cn, (short)exp, na, (short)cvvv, aid);
		if(cCard==null){
			if(helper.insertPayMethod(card)){
				change_success("saved card");
				listener.cardrefresh();
				this.dispose();
				return;
			}
			change_filed("cannot save card");
			return;
		}else{
			if(helper.updateCard(cCard, card)){
				change_success("saved card");
				listener.cardrefresh();
				this.dispose();
				return;
			}
			change_filed("cannot save card");
			return;
		}
	}
	public void delCard(){
		if(helper.deleteCard(cCard)){
			change_success("card deleted");
			listener.cardrefresh();
			this.dispose();
			return;
		}
		change_filed("cannot delete card,make sure this card are not using");
		return;
	}
	
	public interface OnCardUpdate{
		public void cardrefresh();
	}

	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
 }
}
