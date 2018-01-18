package airline;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.FlightLine;

public class FlightLineTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8910743493233690358L;
	private String[] columnNames={"FromDate","ToDate","Path","fly time","Prices"};
	private ArrayList<FlightLine> lines;
	
	public FlightLineTableModel() {
		// TODO 自动生成的构造函数存根
		lines = new ArrayList<>();
	}
	public FlightLineTableModel(ArrayList<FlightLine> lines){
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
			FlightLine fl = lines.get(rowIndex);
			switch (columnIndex) {
			case 0: return fl.getDeptDate()+" "+fl.getFlyTime_wo();
			case 1: return fl.getArrive_date()+" "+fl.getArrive_time_wo();
			case 2: return fl.getLine();
			case 3: return fl.getLength();
			case 4: return String.format("$%.2f", fl.getPrice());
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
	public void changeData(ArrayList<FlightLine> list){
		lines=list;
		fireTableDataChanged();
	}

}
