package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import airline.DetailsTableMode.OnTableUpdate;
import models.Airport;
import models.CreditCard;
import models.Schedule;
import models.UserInfo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class OrderDialog extends JDialog implements OnTableUpdate{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3490517505021759355L;
	private final JPanel contentPanel = new JPanel();
	private DataHelper helper;
	private ArrayList<Schedule> list;
	private DetailsTableMode mode;
	private float price;
	private JLabel lblTotalprice;
	private JTable table;
	private OrderSubmit listener;
	private UserInfo userInfo;
	private JComboBox<CreditCard> comboBox;
	private boolean returnTrip;

	public OrderDialog(final String from,final String to,final  String date1,final String date2,final ArrayList<Schedule>line1,final ArrayList<Schedule>line2,Object obj) {
		price = 0;
		if(obj!=null)
			listener=(OrderSubmit)obj;
		setModal(true);
		userInfo = DataStorage.dStorage.userInfo;
		helper=DataStorage.dStorage.helper;
		setBounds(100, 100, 859, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[164.00][grow]", "[][][][][][][grow]"));
		{
			JLabel lblFrom = new JLabel("From");
			contentPanel.add(lblFrom, "cell 0 0");
		}
		{
			Airport fromap = helper.getAirport(from);
			JLabel lblFromdetails = new JLabel(fromap.getAirportName());
			contentPanel.add(lblFromdetails, "cell 1 0,alignx left");
		}
		{
			JLabel lblTo = new JLabel("to");
			contentPanel.add(lblTo, "cell 0 1");
		}
		{
			Airport toap = helper.getAirport(to);
			JLabel lblTodetails = new JLabel(toap.getAirportName());
			contentPanel.add(lblTodetails, "cell 1 1");
		}
		{
			JLabel lblDepartureDate = new JLabel("Departure Date");
			contentPanel.add(lblDepartureDate, "cell 0 2");
		}
		{
			JLabel lbldepDate = new JLabel(date1);
			contentPanel.add(lbldepDate, "cell 1 2");
		}
		{
			JLabel lblRetuenDate = new JLabel("Retuen Date");
			contentPanel.add(lblRetuenDate, "cell 0 3");
		}
		{
			JLabel lblReturndate = new JLabel(date2==null?"single Trip":date2);
			contentPanel.add(lblReturndate, "cell 1 3");
		}
		{
			JLabel lblPrice = new JLabel("price");
			contentPanel.add(lblPrice, "cell 0 4");
		}
		{
			lblTotalprice = new JLabel("totalPrice");
			contentPanel.add(lblTotalprice, "cell 1 4");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						placeOrder(from, to, line1, line2, date1);
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
						OrderDialog.this.dispose();
					}
				});
			}
		}
		helper=DataStorage.dStorage.helper;
		//helper.getSchedule(acode, fn, date);
		returnTrip = line2 != null;
		list = new ArrayList<>();
		list.addAll(line1);
		if(returnTrip)
			list.addAll(line2);
		mode = new DetailsTableMode(this, list);
		{
			JLabel lblPatmentMtehod = new JLabel("patment mtehod");
			contentPanel.add(lblPatmentMtehod, "cell 0 5,alignx trailing");
		}
		{
			ArrayList<CreditCard> cards = helper.getUserCards(DataStorage.dStorage.userInfo);
			CreditCard[] creditCards = new CreditCard[cards.size()];
			cards.toArray(creditCards);
			comboBox = new JComboBox<CreditCard>(creditCards);
			comboBox.setSelectedIndex(-1);
			contentPanel.add(comboBox, "cell 1 5,growx");
		}
		table = new JTable(mode);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, "cell 0 6 2 1,grow");
		scrollPane.setViewportView(table);
		

		updatePrice();
		
	}
	private void book_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(OrderDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void book_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(OrderDialog.this,
				"order placed",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	private void placeOrder(String from, String to,ArrayList<Schedule> list1,ArrayList<Schedule>list2,String date){
		if(comboBox.getSelectedIndex()<0){
			book_filed("please select a payment method");
			return;
		}
		try{
			if(!helper.insertBooking(price, userInfo.getUid(),((CreditCard)comboBox.getSelectedItem()).getCardNumber(), from, to, list.size(), list2!=null, date)){
				book_filed("can not place your order");
				return;
			}
			for(Schedule x:list1){
				
				if(!helper.insertBookingSchedule(x, false))
					throw new Exception("order failed");
			}
			if(list2!=null){
				for(Schedule x:list2){
					if(!helper.insertBookingSchedule(x, true))
						throw new Exception("order failed");
				}
			}
			book_success();
			OrderDialog.this.dispose();
			listener.onOrderSubmit();
			return;
		}catch (Exception e) {
			// TODO: handle exception
			book_filed("can not place your order");
			return;
		}
	}
	
	private void updatePrice(){
		price=0;
		for (Schedule x:list)
			price+=x.getSelectPrice();
		lblTotalprice.setText(String.format("$%.2f", price));
	}

	@Override
	public void onUpdate() {
		updatePrice();
	}
	
	public interface OrderSubmit{
		public void onOrderSubmit();
	}

}
