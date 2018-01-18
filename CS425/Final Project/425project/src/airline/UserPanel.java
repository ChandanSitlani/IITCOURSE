package airline;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import DataBase.DataHelper;
import airline.AddressDetailsDialog.OnAddressUpdate;
import airline.BookingDetailsDialog.BookingdDelete;
import airline.CardDetailsDialog.OnCardUpdate;
import airline.UpdateUserInfoDialog.UpdateUserInfoSuccess;
import models.Address;
import models.Airport;
import models.Booking;
import models.CreditCard;
import models.UserInfo;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class UserPanel extends JPanel implements UpdateUserInfoSuccess,OnAddressUpdate,OnCardUpdate,BookingdDelete{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2673669029250332846L;
	private JButton btnAddressDetails;
	private JButton btnAddAddress;
	private JButton btnDetailsCard;
	private JButton btnAddCard;
	private JLabel lblhomeAP;
	private JLabel lblName;
	private DataHelper helper;
	private JButton btnNewPasswd;
	private UserInfo userInfo;
	private JTable tableAddress;
	private AddressTableModel addressTableModel;
	private CardTableModel cardTableModel;
	private ArrayList<Address> addressList;
	private ArrayList<CreditCard> cardList;
	private JTable tableCards;
	private JTable tableHis;
	private ArrayList <Booking> bookingList;
	private BookingTableModel bookingTableModel;
	private LogoutSuccess listener;
	/**
	 * Create the panel.
	 */
	public UserPanel(Object obj) {
		if(obj!=null)
			listener = (LogoutSuccess)obj;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 119, 129, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		userInfo = DataStorage.dStorage.userInfo;
		helper=DataStorage.dStorage.helper;
		
		JLabel lblHint = new JLabel("welcome");
		GridBagConstraints gbc_lblHint = new GridBagConstraints();
		gbc_lblHint.gridwidth = 5;
		gbc_lblHint.insets = new Insets(0, 0, 5, 0);
		gbc_lblHint.gridx = 0;
		gbc_lblHint.gridy = 0;
		add(lblHint, gbc_lblHint);
		
		JLabel lblUserInformation = new JLabel("user Information");
		GridBagConstraints gbc_lblUserInformation = new GridBagConstraints();
		gbc_lblUserInformation.anchor = GridBagConstraints.WEST;
		gbc_lblUserInformation.gridwidth = 2;
		gbc_lblUserInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserInformation.gridx = 0;
		gbc_lblUserInformation.gridy = 1;
		add(lblUserInformation, gbc_lblUserInformation);
		
		JButton btnLogout = new JButton("logout");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogout.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout.gridx = 4;
		gbc_btnLogout.gridy = 1;
		add(btnLogout, gbc_btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				DataStorage.dStorage.userInfo=null;
				logout_success();
				listener.logouted();
				
			}
		});
		
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		add(lblUsername, gbc_lblUsername);
		
		lblName = new JLabel(String.format("%s %s %s",
				userInfo.getFirstName(),
				userInfo.getMiddleName()==null?"":userInfo.getMiddleName(),
				userInfo.getLastName()));
		
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.gridwidth = 3;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);
		
		Airport airport = helper.getAirport(userInfo.getHomeIATA());
		
		JLabel lblhome = new JLabel("HomeAirport");
		GridBagConstraints gbc_lblhome = new GridBagConstraints();
		gbc_lblhome.anchor = GridBagConstraints.WEST;
		gbc_lblhome.insets = new Insets(0, 0, 5, 5);
		gbc_lblhome.gridx = 1;
		gbc_lblhome.gridy = 3;
		add(lblhome, gbc_lblhome);
		
		lblhomeAP = new JLabel(airport.getAirportName());
		GridBagConstraints gbc_lblhomeAP = new GridBagConstraints();
		gbc_lblhomeAP.anchor = GridBagConstraints.WEST;
		gbc_lblhomeAP.gridwidth = 3;
		gbc_lblhomeAP.insets = new Insets(0, 0, 5, 0);
		gbc_lblhomeAP.gridx = 2;
		gbc_lblhomeAP.gridy = 3;
		add(lblhomeAP, gbc_lblhomeAP);
		
		btnNewPasswd = new JButton("change password");
		btnNewPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewPasswd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ChangePasswordDialog dialog =new ChangePasswordDialog();
				dialog.setAlwaysOnTop(true);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JButton btnUpdateInformation = new JButton("Update info");
		GridBagConstraints gbc_btnUpdateInformation = new GridBagConstraints();
		gbc_btnUpdateInformation.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateInformation.gridx = 1;
		gbc_btnUpdateInformation.gridy = 4;
		add(btnUpdateInformation, gbc_btnUpdateInformation);
		btnUpdateInformation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				UpdateUserInfoDialog dialog =new UpdateUserInfoDialog(UserPanel.this,userInfo);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewPasswd = new GridBagConstraints();
		gbc_btnNewPasswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewPasswd.gridwidth = 2;
		gbc_btnNewPasswd.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewPasswd.gridx = 3;
		gbc_btnNewPasswd.gridy = 4;
		add(btnNewPasswd, gbc_btnNewPasswd);
		
		
		JLabel lblPaymentInformation = new JLabel("Payment information");
		GridBagConstraints gbc_lblPaymentInformation = new GridBagConstraints();
		gbc_lblPaymentInformation.anchor = GridBagConstraints.WEST;
		gbc_lblPaymentInformation.gridwidth = 2;
		gbc_lblPaymentInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaymentInformation.gridx = 0;
		gbc_lblPaymentInformation.gridy = 5;
		add(lblPaymentInformation, gbc_lblPaymentInformation);
		
		btnDetailsCard = new JButton("details");
		GridBagConstraints gbc_btnDetailsCard = new GridBagConstraints();
		gbc_btnDetailsCard.insets = new Insets(0, 0, 5, 5);
		gbc_btnDetailsCard.gridx = 3;
		gbc_btnDetailsCard.gridy = 5;
		add(btnDetailsCard, gbc_btnDetailsCard);
		
		btnAddCard = new JButton("add");
		GridBagConstraints gbc_btnAddCard = new GridBagConstraints();
		gbc_btnAddCard.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCard.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCard.gridx = 4;
		gbc_btnAddCard.gridy = 5;
		add(btnAddCard, gbc_btnAddCard);
		btnAddCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				CardDetailsDialog dialog =new CardDetailsDialog(UserPanel.this,null,addressList);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JScrollPane scrollPaneCard = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCard = new GridBagConstraints();
		gbc_scrollPaneCard.gridwidth = 5;
		gbc_scrollPaneCard.gridheight = 8;
		gbc_scrollPaneCard.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneCard.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCard.gridx = 0;
		gbc_scrollPaneCard.gridy = 6;
		add(scrollPaneCard, gbc_scrollPaneCard);
		btnDetailsCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int x = tableCards.getSelectedRow();
				if(x<0){
					alert_dialog("please select a card");
					return;
				}
				CreditCard card = cardList.get(x);
				CardDetailsDialog dialog =new CardDetailsDialog(UserPanel.this,card,addressList);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		
		cardList=helper.getUserCards(userInfo);
		cardTableModel = new CardTableModel(cardList);
		tableCards = new JTable(cardTableModel);
		tableCards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneCard.setViewportView(tableCards);
		
		JLabel lblAddressBook = new JLabel("Address book:");
		GridBagConstraints gbc_lblAddressBook = new GridBagConstraints();
		gbc_lblAddressBook.anchor = GridBagConstraints.WEST;
		gbc_lblAddressBook.gridwidth = 2;
		gbc_lblAddressBook.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddressBook.gridx = 0;
		gbc_lblAddressBook.gridy = 14;
		add(lblAddressBook, gbc_lblAddressBook);
		
		btnAddressDetails = new JButton("details");
		GridBagConstraints gbc_btnAddressDetails = new GridBagConstraints();
		gbc_btnAddressDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddressDetails.gridx = 3;
		gbc_btnAddressDetails.gridy = 14;
		add(btnAddressDetails, gbc_btnAddressDetails);
		btnAddressDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int x = tableAddress.getSelectedRow();
				if(x<0){
					alert_dialog("please select a address");
					return;
				}
				Address a = addressList.get(x);
				AddressDetailsDialog dialog =new AddressDetailsDialog(UserPanel.this,a);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnAddAddress = new JButton("add");
		GridBagConstraints gbc_btnAddAddress = new GridBagConstraints();
		gbc_btnAddAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddAddress.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddAddress.gridx = 4;
		gbc_btnAddAddress.gridy = 14;
		add(btnAddAddress, gbc_btnAddAddress);
		btnAddAddress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				AddressDetailsDialog dialog =new AddressDetailsDialog(UserPanel.this,null);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JScrollPane scrollPaneAddress = new JScrollPane();
		GridBagConstraints gbc_scrollPaneAddress = new GridBagConstraints();
		gbc_scrollPaneAddress.gridheight = 3;
		gbc_scrollPaneAddress.gridwidth = 5;
		gbc_scrollPaneAddress.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneAddress.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneAddress.gridx = 0;
		gbc_scrollPaneAddress.gridy = 15;
		add(scrollPaneAddress, gbc_scrollPaneAddress);
		
		addressList=helper.getUserAddress(userInfo);
		addressTableModel = new AddressTableModel(addressList);
		tableAddress = new JTable(addressTableModel);
		tableAddress.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneAddress.setViewportView(tableAddress);
		
		JLabel lblOrderHistory = new JLabel("Order history:");
		GridBagConstraints gbc_lblOrderHistory = new GridBagConstraints();
		gbc_lblOrderHistory.anchor = GridBagConstraints.WEST;
		gbc_lblOrderHistory.gridwidth = 2;
		gbc_lblOrderHistory.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderHistory.gridx = 0;
		gbc_lblOrderHistory.gridy = 18;
		add(lblOrderHistory, gbc_lblOrderHistory);
		
		JButton btnMileage = new JButton("mileage");
		GridBagConstraints gbc_btnMileage = new GridBagConstraints();
		gbc_btnMileage.anchor = GridBagConstraints.EAST;
		gbc_btnMileage.insets = new Insets(0, 0, 5, 5);
		gbc_btnMileage.gridx = 2;
		gbc_btnMileage.gridy = 18;
		add(btnMileage, gbc_btnMileage);
		btnMileage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				MileageDialog dialog =new MileageDialog(userInfo);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JButton btnRefresh = new JButton("refresh");
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefresh.gridx = 3;
		gbc_btnRefresh.gridy = 18;
		add(btnRefresh, gbc_btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				orderfresh();
			}
		});
		
		JButton btnDetails = new JButton("details");
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.gridx = 4;
		gbc_btnDetails.gridy = 18;
		add(btnDetails, gbc_btnDetails);
		btnDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int x = tableHis.getSelectedRow();
				if(x<0){
					alert_dialog("please select a order");
					return;
				}
				Booking b = bookingList.get(x);
				BookingDetailsDialog dialog =new BookingDetailsDialog(UserPanel.this,b);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JScrollPane scrollPaneHis = new JScrollPane();
		GridBagConstraints gbc_scrollPaneHis = new GridBagConstraints();
		gbc_scrollPaneHis.gridheight = 3;
		gbc_scrollPaneHis.gridwidth = 5;
		gbc_scrollPaneHis.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneHis.gridx = 0;
		gbc_scrollPaneHis.gridy = 19;
		add(scrollPaneHis, gbc_scrollPaneHis);
		
		bookingList = helper.getUserBooking(userInfo.getUid());
		bookingTableModel = new BookingTableModel(bookingList);
		tableHis = new JTable(bookingTableModel);
		tableHis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneHis.setViewportView(tableHis);

	}
	private void alert_dialog(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(UserPanel.this,
				info,
				"notice",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}

	@Override
	public void onUpdateUinfoSuccess() {
		// TODO 自动生成的方法存根
		userInfo = DataStorage.dStorage.userInfo;
		lblName.setText(String.format("%s %s %s",
				userInfo.getFirstName(),
				userInfo.getMiddleName()==null?"":userInfo.getMiddleName(),
				userInfo.getLastName()));
		Airport airport = helper.getAirport(userInfo.getHomeIATA());
		lblhomeAP.setText(airport.getAirportName());
	}

	@Override
	public void addressrefresh() {
		// TODO 自动生成的方法存根
		addressList=helper.getUserAddress(userInfo);
		addressTableModel.changeData(addressList);
	}
	@Override
	public void cardrefresh() {
		// TODO 自动生成的方法存根
		cardList = helper.getUserCards(userInfo);
		cardTableModel.changeData(cardList);
		
	}
	public void orderfresh(){
		bookingList=helper.getUserBooking(userInfo.getUid());
		bookingTableModel.changeData(bookingList);
	}
	private void logout_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(UserPanel.this,
				"logouted ",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	public interface LogoutSuccess{
		public void logouted();
	}
	

}
