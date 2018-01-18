package airline;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Booking;

public class BookingTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3733579734071309965L;
	private String[] columnNames={"orderNumber","from","to","date","fligntCount","price","payment method","roundTrip"};
	private ArrayList<Booking> books;
	
	public BookingTableModel() {
		// TODO 自动生成的构造函数存根
		books = new ArrayList<>();
	}
	public BookingTableModel(ArrayList<Booking> books){
		this.books=books;
	}
	
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return books==null?0:books.size();
	}

	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public boolean isCellEditable(int row,int col){
		return false;
	}
	public void changeData(ArrayList<Booking> list){
		books=list;
		fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		try{
			Booking book = books.get(rowIndex);
			switch (columnIndex){
			case 0: return book.getOrderNumber();
			case 1: return book.getFromAP();
			case 2: return book.getToAP();
			case 3: return book.getStdate();
			case 4: return book.getFlightNum();
			case 5: return book.getPrice();
			case 6: return "****"+book.getCardNumber().substring(book.getCardNumber().length()-4);
			case 7: return book.isIsreturn();
			default: return null;
			}
 		}catch (Exception e) {
			// TODO: handle exception
 			return null;
		}
		
	}

}
