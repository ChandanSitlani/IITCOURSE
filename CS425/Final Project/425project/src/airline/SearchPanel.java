package airline;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import models.FlightLine;

import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

public class SearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> fromBox;
	JButton btnSearch;
	JSpinner Return ,date;
	JCheckBox chckbxRoundTrip,chckbxSelectdate;
	private JComboBox<Object> toBox;
	private JComboBox<Object> fromIATA;
	private JComboBox<Object> toIATA;
	private JTextField txtTime;
	private JComboBox<Object> timeBox;
	private JTextField txtMoney;
	private JTextField txtMaxcon;
	private SearchInterface listener;
	private boolean selectedDate;
	private JComboBox<String> orderByBox;
	private JLabel lblOrderby;
	private JCheckBox chckbxTransferTime;
	private JTextField txtLotrt;
	private JTextField txtUptrt;
	private JLabel label_1;
	private JLabel lblHours;


	/**
	 * Create the panel.
	 */
	public SearchPanel(Object obj) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		if(obj!=null)
			listener=(SearchInterface)obj;
		gridBagLayout.columnWidths = new int[]{75, 125, 81, 93, 47, 112, 99, 0, 12, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 9, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		selectedDate=false;
		
		JLabel lblFrom = new JLabel("From:");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 0;
		gbc_lblFrom.gridy = 0;
		add(lblFrom, gbc_lblFrom);
		
		JLabel lblCountry = new JLabel("Country\uFF1A");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.EAST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 1;
		gbc_lblCountry.gridy = 0;
		add(lblCountry, gbc_lblCountry);
		
		fromBox = new JComboBox<Object>(DataStorage.dStorage.countries.toArray());
		GridBagConstraints gbc_fromBox = new GridBagConstraints();
		gbc_fromBox.gridwidth = 3;
		gbc_fromBox.insets = new Insets(0, 0, 5, 5);
		gbc_fromBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fromBox.gridx = 2;
		gbc_fromBox.gridy = 0;
		add(fromBox, gbc_fromBox);
		fromBox.setSelectedIndex(-1);
		fromBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				fromChange();
			}
		});
		
		JLabel lblCity = new JLabel("Airport\uFF1A");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 5;
		gbc_lblCity.gridy = 0;
		add(lblCity, gbc_lblCity);
		
		fromIATA = new JComboBox<Object>();
		GridBagConstraints gbc_fromIATA = new GridBagConstraints();
		gbc_fromIATA.gridwidth = 2;
		gbc_fromIATA.insets = new Insets(0, 0, 5, 5);
		gbc_fromIATA.fill = GridBagConstraints.HORIZONTAL;
		gbc_fromIATA.gridx = 6;
		gbc_fromIATA.gridy = 0;
		add(fromIATA, gbc_fromIATA);
		
		JLabel lblTo = new JLabel("To:");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 0;
		gbc_lblTo.gridy = 1;
		add(lblTo, gbc_lblTo);
		
		SpinnerDateModel mode = new SpinnerDateModel();
		
		JLabel lblCountry_1 = new JLabel("Country\uFF1A");
		GridBagConstraints gbc_lblCountry_1 = new GridBagConstraints();
		gbc_lblCountry_1.anchor = GridBagConstraints.EAST;
		gbc_lblCountry_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry_1.gridx = 1;
		gbc_lblCountry_1.gridy = 1;
		add(lblCountry_1, gbc_lblCountry_1);
		
		toBox = new JComboBox<Object>( DataStorage.dStorage.countries.toArray());
		GridBagConstraints gbc_toBox = new GridBagConstraints();
		gbc_toBox.gridwidth = 3;
		gbc_toBox.insets = new Insets(0, 0, 5, 5);
		gbc_toBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_toBox.gridx = 2;
		gbc_toBox.gridy = 1;
		add(toBox, gbc_toBox);
		toBox.setSelectedIndex(-1);
		toBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				toChange();
			}
		});
		
		JLabel lblCity_1 = new JLabel("Airport\uFF1A");
		GridBagConstraints gbc_lblCity_1 = new GridBagConstraints();
		gbc_lblCity_1.anchor = GridBagConstraints.EAST;
		gbc_lblCity_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity_1.gridx = 5;
		gbc_lblCity_1.gridy = 1;
		add(lblCity_1, gbc_lblCity_1);
		
		toIATA = new JComboBox<Object>();
		GridBagConstraints gbc_toIATA = new GridBagConstraints();
		gbc_toIATA.gridwidth = 2;
		gbc_toIATA.insets = new Insets(0, 0, 5, 5);
		gbc_toIATA.fill = GridBagConstraints.HORIZONTAL;
		gbc_toIATA.gridx = 6;
		gbc_toIATA.gridy = 1;
		add(toIATA, gbc_toIATA);
		
		JLabel lblAdvSearch = new JLabel("ADV search");
		GridBagConstraints gbc_lblAdvSearch = new GridBagConstraints();
		gbc_lblAdvSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdvSearch.gridx = 0;
		gbc_lblAdvSearch.gridy = 2;
		add(lblAdvSearch, gbc_lblAdvSearch);
		
		JLabel lblLimts = new JLabel("Limts:");
		GridBagConstraints gbc_lblLimts = new GridBagConstraints();
		gbc_lblLimts.anchor = GridBagConstraints.EAST;
		gbc_lblLimts.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimts.gridx = 1;
		gbc_lblLimts.gridy = 2;
		add(lblLimts, gbc_lblLimts);
		
		txtTime = new JTextField();
		txtTime.setText("");
		GridBagConstraints gbc_txtTime = new GridBagConstraints();
		gbc_txtTime.insets = new Insets(0, 0, 5, 5);
		gbc_txtTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTime.gridx = 2;
		gbc_txtTime.gridy = 2;
		add(txtTime, gbc_txtTime);
		txtTime.setEnabled(false);
		txtTime.setColumns(10);
		
		timeBox = new JComboBox<Object>(new String []{"None","hour","day"});
		GridBagConstraints gbc_timeBox = new GridBagConstraints();
		gbc_timeBox.insets = new Insets(0, 0, 5, 5);
		gbc_timeBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeBox.gridx = 3;
		gbc_timeBox.gridy = 2;
		add(timeBox, gbc_timeBox);
		timeBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				timeLimtChange();
			}
		});
		
		JLabel label = new JLabel("$");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		
		txtMoney = new JTextField();
		txtMoney.setText("");
		GridBagConstraints gbc_txtMoney = new GridBagConstraints();
		gbc_txtMoney.insets = new Insets(0, 0, 5, 5);
		gbc_txtMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMoney.gridx = 5;
		gbc_txtMoney.gridy = 2;
		add(txtMoney, gbc_txtMoney);
		txtMoney.setColumns(10);
		
		JLabel lblConnect = new JLabel("connect:");
		GridBagConstraints gbc_lblConnect = new GridBagConstraints();
		gbc_lblConnect.anchor = GridBagConstraints.EAST;
		gbc_lblConnect.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnect.gridx = 6;
		gbc_lblConnect.gridy = 2;
		add(lblConnect, gbc_lblConnect);
		
		txtMaxcon = new JTextField();
		txtMaxcon.setText("");
		GridBagConstraints gbc_txtMaxcon = new GridBagConstraints();
		gbc_txtMaxcon.insets = new Insets(0, 0, 5, 5);
		gbc_txtMaxcon.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxcon.gridx = 7;
		gbc_txtMaxcon.gridy = 2;
		add(txtMaxcon, gbc_txtMaxcon);
		txtMaxcon.setColumns(10);
		
		chckbxSelectdate = new JCheckBox("selectDate");
		GridBagConstraints gbc_chckbxSelectdate = new GridBagConstraints();
		gbc_chckbxSelectdate.anchor = GridBagConstraints.WEST;
		gbc_chckbxSelectdate.gridwidth = 2;
		gbc_chckbxSelectdate.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSelectdate.gridx = 2;
		gbc_chckbxSelectdate.gridy = 3;
		add(chckbxSelectdate, gbc_chckbxSelectdate);
		chckbxSelectdate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(selectedDate == chckbxSelectdate.isSelected())
					return;
				chckbxRoundTrip.setEnabled(chckbxSelectdate.isSelected());
				chckbxRoundTrip.setSelected(false);
				selectedDate=chckbxSelectdate.isSelected();
				date.setEnabled(chckbxSelectdate.isSelected());
			}
		});
		
		SpinnerDateModel mode1 = new SpinnerDateModel();
		
		chckbxRoundTrip = new JCheckBox("Round Trip");
		chckbxRoundTrip.setEnabled(false);
		chckbxRoundTrip.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Return.setEnabled(chckbxRoundTrip.isSelected());
			}
		});
		GridBagConstraints gbc_chckbxRoundTrip = new GridBagConstraints();
		gbc_chckbxRoundTrip.anchor = GridBagConstraints.WEST;
		gbc_chckbxRoundTrip.gridwidth = 2;
		gbc_chckbxRoundTrip.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRoundTrip.gridx = 6;
		gbc_chckbxRoundTrip.gridy = 3;
		add(chckbxRoundTrip, gbc_chckbxRoundTrip);
		
		JLabel lblLeave = new JLabel("leave");
		GridBagConstraints gbc_lblLeave = new GridBagConstraints();
		gbc_lblLeave.anchor = GridBagConstraints.EAST;
		gbc_lblLeave.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeave.gridx = 1;
		gbc_lblLeave.gridy = 4;
		add(lblLeave, gbc_lblLeave);
		
		date = new JSpinner(mode);
		JSpinner.DateEditor de_date_1=new JSpinner.DateEditor(date, "yyyy-MM-dd");
		date.setEditor(de_date_1);
		GridBagConstraints gbc_date_1 = new GridBagConstraints();
		gbc_date_1.gridwidth = 2;
		gbc_date_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_1.insets = new Insets(0, 0, 5, 5);
		gbc_date_1.gridx = 2;
		gbc_date_1.gridy = 4;
		add(date, gbc_date_1);
		date.setEnabled(false);
		
		JLabel lblReturn = new JLabel("return");
		GridBagConstraints gbc_lblReturn = new GridBagConstraints();
		gbc_lblReturn.gridwidth = 2;
		gbc_lblReturn.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturn.gridx = 4;
		gbc_lblReturn.gridy = 4;
		add(lblReturn, gbc_lblReturn);
		Return = new JSpinner(mode1);
		Return.setEnabled(false);
		JSpinner.DateEditor editoR=new JSpinner.DateEditor(Return, "yyyy-MM-dd");
		Return.setEditor(editoR);
		GridBagConstraints gbc_return = new GridBagConstraints();
		gbc_return.gridwidth = 2;
		gbc_return.fill = GridBagConstraints.HORIZONTAL;
		gbc_return.insets = new Insets(0, 0, 5, 5);
		gbc_return.gridx = 6;
		gbc_return.gridy = 4;
		add(Return, gbc_return);
		
		chckbxTransferTime = new JCheckBox("transTime");
		GridBagConstraints gbc_chckbxTransferTime = new GridBagConstraints();
		gbc_chckbxTransferTime.anchor = GridBagConstraints.WEST;
		gbc_chckbxTransferTime.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTransferTime.gridx = 1;
		gbc_chckbxTransferTime.gridy = 5;
		add(chckbxTransferTime, gbc_chckbxTransferTime);
		chckbxTransferTime.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO 自动生成的方法存根
				if(!chckbxTransferTime.isSelected()){
					txtLotrt.setText("3");
					txtUptrt.setText("24");
				}
				txtLotrt.setEnabled(chckbxTransferTime.isSelected());
				txtUptrt.setEnabled(chckbxTransferTime.isSelected());
				
			}
		});
		
		txtLotrt = new JTextField();
		txtLotrt.setEnabled(false);
		txtLotrt.setText("3");
		GridBagConstraints gbc_txtLotrt = new GridBagConstraints();
		gbc_txtLotrt.gridwidth = 2;
		gbc_txtLotrt.insets = new Insets(0, 0, 5, 5);
		gbc_txtLotrt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLotrt.gridx = 2;
		gbc_txtLotrt.gridy = 5;
		add(txtLotrt, gbc_txtLotrt);
		txtLotrt.setColumns(10);
		
		label_1 = new JLabel("~");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.SOUTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 5;
		add(label_1, gbc_label_1);
		
		txtUptrt = new JTextField();
		txtUptrt.setEnabled(false);
		txtUptrt.setText("24");
		GridBagConstraints gbc_txtUptrt = new GridBagConstraints();
		gbc_txtUptrt.gridwidth = 2;
		gbc_txtUptrt.insets = new Insets(0, 0, 5, 5);
		gbc_txtUptrt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUptrt.gridx = 5;
		gbc_txtUptrt.gridy = 5;
		add(txtUptrt, gbc_txtUptrt);
		txtUptrt.setColumns(10);
		
		lblHours = new JLabel("hour(s)");
		GridBagConstraints gbc_lblHours = new GridBagConstraints();
		gbc_lblHours.insets = new Insets(0, 0, 5, 5);
		gbc_lblHours.gridx = 7;
		gbc_lblHours.gridy = 5;
		add(lblHours, gbc_lblHours);
				
		lblOrderby = new JLabel("orderBy");
		GridBagConstraints gbc_lblOrderby = new GridBagConstraints();
		gbc_lblOrderby.anchor = GridBagConstraints.EAST;
		gbc_lblOrderby.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderby.gridx = 1;
		gbc_lblOrderby.gridy = 6;
		add(lblOrderby, gbc_lblOrderby);
				
				orderByBox = new JComboBox<String>(new String[]{"none","connect","length","price"});//
				GridBagConstraints gbc_orderByBox = new GridBagConstraints();
				gbc_orderByBox.gridwidth = 2;
				gbc_orderByBox.insets = new Insets(0, 0, 5, 5);
				gbc_orderByBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_orderByBox.gridx = 2;
				gbc_orderByBox.gridy = 6;
				add(orderByBox, gbc_orderByBox);
				
						
						btnSearch = new JButton("Search");
						GridBagConstraints gbc_btnSearch = new GridBagConstraints();
						gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
						gbc_btnSearch.anchor = GridBagConstraints.WEST;
						gbc_btnSearch.gridx = 7;
						gbc_btnSearch.gridy = 6;
						add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				search_flight(false);
			}
		});

	}
	
	private void fromChange(){
		String select= (String)fromBox.getSelectedItem();
		ArrayList<String> iatas = DataStorage.dStorage.helper.countryAirport(select);
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>(iatas.toArray());
		fromIATA.setModel(model);
		fromIATA.setSelectedIndex(-1);
	}
	
	private void toChange(){
		String select= (String)toBox.getSelectedItem();
		ArrayList<String> iatas = DataStorage.dStorage.helper.countryAirport(select);
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>(iatas.toArray());
		toIATA.setModel(model);
		toIATA.setSelectedIndex(-1);
	}
	private void timeLimtChange(){
		int selet=timeBox.getSelectedIndex();
		if(selet==0){
			txtTime.setText("");
			txtTime.setEnabled(false);
		}else{
			txtTime.setEnabled(true);
		}
	}
	private void search_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(SearchPanel.this,
				info,
				"error",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void search_success(int size){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(SearchPanel.this,
				String.format("get %d lines", size),
				"message",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	protected void search_flight(boolean isreturn){
		String from = (String)fromIATA.getSelectedItem();
		if(from==null){
			search_filed("please select depart");
			return;
		}
		String to= (String)toIATA.getSelectedItem();
		if(to==null){
			search_filed("please select destination");
			return;
		}
		boolean limitime = timeBox.getSelectedIndex()!=0;
		String timelimt;
		if(limitime){
			try{
				int ti = Integer.parseInt(txtTime.getText());
				timelimt = String.format("%d %s", ti, (String)timeBox.getSelectedItem());
			}catch (Exception e) {
				search_filed("time limt error");
				return;
			}
		}else{
			timelimt = "1 day";
		}
		boolean limtmoney=txtMoney.getText().length()>0;
		float moneylimt;
		if(limtmoney){
			try{
				moneylimt=Float.parseFloat(txtMoney.getText());
				limtmoney = moneylimt>0;
			}catch (Exception e) {
				search_filed("money limt error");
				return;
			}
		}else{
			moneylimt=1;
		}
		boolean limtconn=txtMaxcon.getText().length()>0;
		int connlimt;
		if(limtconn){
			try{
				connlimt=Integer.parseInt(txtMaxcon.getText());
				if(connlimt==0){
					connlimt=5;
				}
			}catch (Exception e) {
				search_filed("connection limt error");
				return;
			}
		}else{
			connlimt=5;
		}
		ArrayList<FlightLine>lines;
		if(!isreturn){
			String selectedDate = chckbxSelectdate.isSelected()? date.getValue().toString():"2000-01-01";
			if(!chckbxTransferTime.isSelected())
				lines = DataStorage.dStorage.helper.searchFlights(from, to, connlimt, limitime, timelimt, limtmoney, moneylimt,chckbxSelectdate.isSelected(),selectedDate,orderByBox.getSelectedItem().toString());
			else{
				try{
					int lt=Integer.parseInt(txtLotrt.getText().trim());
					int ut=Integer.parseInt(txtUptrt.getText().trim());
					if(ut<=lt){
						search_filed("input time error");
						return;
					}
					String tsLow = String.format("%d hour", lt);
					String tsUp = String.format("%d hour", ut);
					lines = DataStorage.dStorage.helper.searchFlights(from, to, connlimt, tsLow, tsUp, limitime, timelimt, limtmoney, moneylimt,chckbxSelectdate.isSelected(),selectedDate,orderByBox.getSelectedItem().toString());
				}catch (NumberFormatException e) {
					// TODO: handle exception
					search_filed("input time error");
					return;
				}
			}
		}else{
			String selectedDate =Return.getValue().toString();
			if(!chckbxTransferTime.isSelected())
				lines = DataStorage.dStorage.helper.searchFlights(to, from, connlimt, limitime, timelimt, limtmoney, moneylimt,true,selectedDate,orderByBox.getSelectedItem().toString());
			else{
				try{
					int lt=Integer.parseInt(txtLotrt.getText().trim());
					int ut=Integer.parseInt(txtUptrt.getText().trim());
					if(ut<=lt){
						search_filed("input time error");
						return;
					}
					String tsLow = String.format("%d hour", lt);
					String tsUp = String.format("%d hour", ut);
					lines = DataStorage.dStorage.helper.searchFlights(to, from, connlimt, tsLow, tsUp, limitime, timelimt, limtmoney, moneylimt,true,selectedDate,orderByBox.getSelectedItem().toString());
				}catch (NumberFormatException e) {
					// TODO: handle exception
					search_filed("input time error");
					return;
				}
			}
		}
		if(lines==null){
			search_filed("search filed");
			return;
		}
		search_success(lines.size());
		listener.OnResultCatch(lines);
	}
	public Date getRetDate(){
		return (Date)Return.getValue();
	}
	public void setSearchEnable(boolean enable){
		btnSearch.setEnabled(enable);
	}
	public interface SearchInterface{
		public void OnResultCatch(ArrayList<FlightLine> lines);
		public void OnResultSelected();
	}
	public boolean isReturn(){
		return chckbxRoundTrip.isSelected();
	}
	
}
