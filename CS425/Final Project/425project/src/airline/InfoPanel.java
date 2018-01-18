package airline;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import airline.FlightLineDetailsDialog.SelectedFL;
import airline.OrderDialog.OrderSubmit;
import airline.SearchPanel.SearchInterface;
import models.FlightLine;
import models.Schedule;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InfoPanel extends JPanel implements SearchInterface,SelectedFL,OrderSubmit{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1083104980526076788L;
	private JTable table;
	private FlightLineTableModel model;
	private ArrayList<FlightLine> lines;
	private JPanel keypanel;
	private JButton bthcancel;
	private SearchPanel panel;
	private ArrayList<Schedule> selected;
	private FlightLine seLine;
	private JLabel lblOrderhint;
	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new SearchPanel(this);
		add(panel, BorderLayout.NORTH);
		
		keypanel = new JPanel();
		add(keypanel, BorderLayout.SOUTH);
		keypanel.setLayout(new MigLayout("", "[29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][29px][][29px][29px]", "[29px]"));
		
		lblOrderhint = new JLabel("orderHint");
		keypanel.add(lblOrderhint, "cell 0 0 16 1");
		
		bthcancel = new JButton("cancel");
		keypanel.add(bthcancel, "cell 17 0,alignx right");
		bthcancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				releasrSelect();
			}
		});
		
		JButton btnDetails = new JButton("details");
		keypanel.add(btnDetails, "cell 18 0,alignx right");
		btnDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int x = table.getSelectedRow();
				if(x<0){
					select_filed();
					return;
				}
				FlightLine line = lines.get(x);
				FlightLineDetailsDialog dialog = new FlightLineDetailsDialog(line,InfoPanel.this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		model= new FlightLineTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		releasrSelect();

	}
	private void select_filed(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(InfoPanel.this,
				"please select a FlightLines",
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,options,options[0]);
	}

	@Override
	public void OnResultCatch(ArrayList<FlightLine> lines) {
		// TODO 自动生成的方法存根
		model.changeData(lines);
		this.lines=lines;
	}

	@Override
	public void OnResultSelected() {
		// TODO 自动生成的方法存根
	}
	
	public void releasrSelect(){
		selected = null;
		seLine=null;
		bthcancel.setVisible(false);
		lblOrderhint.setVisible(false);
		panel.setSearchEnable(true);
		model.changeData(null);
		lines=null;
	}
	public void selected(ArrayList<Schedule> list){
		seLine = lines.get(table.getSelectedRow());
		selected = list;
		bthcancel.setVisible(true);
		lblOrderhint.setVisible(true);
		panel.setSearchEnable(false);
	}
	@Override
	public void onselect(ArrayList<Schedule> selectlist) {
		// TODO 自动生成的方法存根
		boolean returnT=panel.isReturn();
		if(returnT){
			if(selected==null){
				selected(selectlist);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");		
				try {
					Date rDate=panel.getRetDate();
					Date toDate = sdf.parse(seLine.getArrive_date());
					if(!toDate.before(rDate))
						throw new Exception("Date error");
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					alert("date error");
					return;
					
				}
				panel.search_flight(true);
			}else{
				String iatas[] = seLine.getIATAs();
				OrderDialog dialog = new OrderDialog(iatas[0], iatas[iatas.length-1], seLine.getDeptDate(), seLine.getArrive_date(), selected, selectlist, InfoPanel.this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}else{
			FlightLine line =lines.get(table.getSelectedRow());
			String iatas[] = line.getIATAs();
			OrderDialog dialog = new OrderDialog(iatas[0], iatas[iatas.length-1], line.getDeptDate(), line.getArrive_date(), selectlist, null , InfoPanel.this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
	
	private void alert(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(InfoPanel.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	@Override
	public void onOrderSubmit() {
		// TODO 自动生成的方法存根
		releasrSelect();
	}

}
