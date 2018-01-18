package airline;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Address;

public class AddressTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7258109010440923110L;
	private String[] columnNames={"addressID","street","deptNum","city","state","zipcode","country","phonenumber"};
	private ArrayList<Address> lines;
	
	public AddressTableModel() {
		// TODO 自动生成的构造函数存根
		lines = new ArrayList<>();
	}
	public AddressTableModel(ArrayList<Address> lines){
		this.lines=lines;
	}
	
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return lines==null?0:lines.size();
	}

	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		try{
			Address address = lines.get(rowIndex);
			switch (columnIndex) {
			case 0:return address.getAddrID();
			case 1:return address.getStreet();
			case 2:return address.getDeptNum();
			case 3:return address.getCity();
			case 4:return address.getState();
			case 5:return address.getZipCode();
			case 6:return address.getCountry();
			case 7:return address.getPhoneNumber();
			default:return null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override

	public boolean isCellEditable(int row,int col){
		return false;
	}
	public void changeData(ArrayList<Address> list){
		lines=list;
		fireTableDataChanged();
	}

}
