package airline;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import airline.DetailsTableMode.OnTableUpdate;
import models.FlightLine;
import models.Schedule;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class FlightLineDetailsDialog extends JDialog implements OnTableUpdate{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8061687855839451196L;
	private final JPanel contentPanel = new JPanel();
	private DataHelper helper;
	private ArrayList<Schedule> list;
	private DetailsTableMode mode;
	private float price;
	private JLabel lblTotalprice;
	private JTable table;
	private SelectedFL listener;

	public FlightLineDetailsDialog(FlightLine line,Object obj) {
		price = 0;
		if(obj!=null)
			listener=(SelectedFL)obj;
		setModal(true);
		setBounds(100, 100, 859, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[164.00][grow]", "[][][][][grow]"));
		{
			JLabel lblFrom = new JLabel("From");
			contentPanel.add(lblFrom, "cell 0 0");
		}
		{
			JLabel lblFromdetails = new JLabel("fromdetails");
			lblFromdetails.setText(line.getDeptDate()+" "+line.getFlyTime_wo());
			contentPanel.add(lblFromdetails, "cell 1 0,alignx left");
		}
		{
			JLabel lblTo = new JLabel("to");
			contentPanel.add(lblTo, "cell 0 1");
		}
		{
			JLabel lblTodetails = new JLabel("todetails");
			lblTodetails.setText(line.getArrive_date()+" "+line.getArrive_time_wo());
			contentPanel.add(lblTodetails, "cell 1 1");
		}
		{
			JLabel lblConnection = new JLabel("connection");
			contentPanel.add(lblConnection, "cell 0 2");
		}
		{
			JLabel lblConnectionnum = new JLabel("ConnectionNum");
			lblConnectionnum.setText(String.format("%d", line.getFlightCount()));
			contentPanel.add(lblConnectionnum, "cell 1 2");
		}
		{
			JLabel lblPrice = new JLabel("price");
			contentPanel.add(lblPrice, "cell 0 3");
		}
		{
			lblTotalprice = new JLabel("totalPrice");
			contentPanel.add(lblTotalprice, "cell 1 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						if(DataStorage.dStorage.userInfo==null){
							alert("please login");
							return;
						}
						FlightLineDetailsDialog.this.dispose();
						listener.onselect(list);
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						FlightLineDetailsDialog.this.dispose();
					}
				});
			}
		}
		helper=DataStorage.dStorage.helper;
		//helper.getSchedule(acode, fn, date);
		String[] dates= line.getDates();
		String[] acodes = line.getFlightS();
		int[] fns = new int[acodes.length];
		list = new ArrayList<>();
		for (int i=0;i<acodes.length;i++){
			String [] temp = acodes[i].split(" ");
			acodes[i]=temp[0];
			fns[i] = Integer.parseInt(temp[1]);
		}
		for (int i=0;i<acodes.length;i++){
			list.add(helper.getSchedule(acodes[i], fns[i], dates[i]));
		}
		mode = new DetailsTableMode(this, list);
		table = new JTable(mode);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, "cell 0 4 2 1,grow");
		scrollPane.setViewportView(table);

		updatePrice();
		
	}
	private void alert(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(FlightLineDetailsDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
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
	public interface SelectedFL{
		public void onselect(ArrayList<Schedule> selectlist);
	}

}
