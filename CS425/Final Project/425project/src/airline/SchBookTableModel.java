package airline;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.ScheduleBooking;

public class SchBookTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656817861029698267L;
	private String[] columnNames={"flightNumber","Date","isReturn","class"};
	private ArrayList<ScheduleBooking> list;
	
	public SchBookTableModel() {
		// TODO 自动生成的构造函数存根
		list = new ArrayList<>();
	}
	public SchBookTableModel(ArrayList<ScheduleBooking> list){
		this.list=list;
	}
	
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return list==null?0:list.size();
	}

	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public boolean isCellEditable(int row,int col){
		return false;
	}
	public void changeData(ArrayList<ScheduleBooking> list){
		this.list=list;
		fireTableDataChanged();
	}
	@Override
	public Object getValueAt(int row, int col) {
		// TODO 自动生成的方法存根
		try{
			ScheduleBooking sBooking = list.get(row);
			switch (col){
			case 0: return sBooking.getAcode()+sBooking.getFlight_number();
			case 1: return sBooking.getDate();
			case 2: return sBooking.isReturnFlight()?"Yes":"No";
			case 3: return sBooking.isFirstclass()?"first":"ecno";
			default:return null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
