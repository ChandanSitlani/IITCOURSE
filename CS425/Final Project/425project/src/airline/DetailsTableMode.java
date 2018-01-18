package airline;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Schedule;

public class DetailsTableMode extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5559498168471597548L;
	private ArrayList<Schedule> data;
	private OnTableUpdate listener;
	private String[] columnNames = {
			"starting",
			"destination",
			"AirLine",
			"flightNumber",
			"schedule date",
			"dept time",
			"arrive time",
			"price",
			"leftCap",
			"FirstClass"};

	public DetailsTableMode(Object obj){
		listener = (OnTableUpdate)obj;
		data=new ArrayList<>();
	}
	public DetailsTableMode(Object obj, ArrayList<Schedule> list) {
		listener = (OnTableUpdate)obj;
		data=list;
	}
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		if(data==null)
			return 0;
		return data.size();
	}

	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)  {
		try{
			Schedule s = data.get(rowIndex);
			switch(columnIndex){
			case 0: return s.getFrom_airport();
			case 1: return s.getTo_airport();
			case 2: return s.getAirName();
			case 3: return s.getAcode()+s.getFlightNumber();
			case 4: return s.getSchedule_date();
			case 5: return s.getFromTimeWo();
			case 6: return s.getToTimeWo()+(s.getPassdate()>0?("("+s.getPassdate()+")"):"");
			case 7: return s.isBookfirstclass()? s.getFirstClassPrice():s.getEconClassPrice();
			case 8: return s.isBookfirstclass()? s.getFirstClassCapLeft():s.getEconClassCapLeft();
			case 9: return s.isBookfirstclass();
			default: return null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public Class<?> getColumnClass(int c){
		if(c==9)
			return Boolean.class;
		return super.getColumnClass(c);
	}
	@Override
	public boolean isCellEditable(int row,int col){
		if(col<9)
			return false;
		try{
			Schedule s = data.get(row);
			return (s.getFirstClassCapLeft()>0&&s.getEconClassCapLeft()>0);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public void changeData(ArrayList<Schedule> list){
		data=list;
		fireTableDataChanged();
	}
	public void setValueAt(Object value,int row,int col){
		Boolean b=(Boolean)value;
		Schedule s =data.get(row);
		if(col!=9)
			return;
		s.setBookfirstclass(b);
		fireTableCellUpdated(row, 8);
		fireTableCellUpdated(row, 7);
		fireTableCellUpdated(row, 9);
		listener.onUpdate();
	}
	
	public interface OnTableUpdate{
		public void onUpdate();
	}
	
	

}
