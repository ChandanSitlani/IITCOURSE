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
import models.Airport;
import models.Booking;
import models.ScheduleBooking;
import net.miginfocom.swing.MigLayout;

public class BookingDetailsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1896134008008666109L;
	private final JPanel contentPanel = new JPanel();
	private DataHelper helper;
	private SchBookTableModel model;
	private JLabel lblTotalprice;
	private JTable table;
	private BookingdDelete listener;
	public BookingDetailsDialog(Object obj, final Booking booking) {
		if(obj!=null)
			listener=(BookingdDelete)obj;
		setModal(true);
		helper=DataStorage.dStorage.helper;
		setBounds(100, 100, 859, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[164.00][grow]", "[][][][][][grow]"));
		{
			JLabel lblFrom = new JLabel("From");
			contentPanel.add(lblFrom, "cell 0 0");
		}
		{
			Airport fromap = helper.getAirport(booking.getFromAP());
			JLabel lblFromdetails = new JLabel(fromap.getAirportName());
			contentPanel.add(lblFromdetails, "cell 1 0,alignx left");
		}
		{
			JLabel lblTo = new JLabel("to");
			contentPanel.add(lblTo, "cell 0 1");
		}
		{
			Airport toap = helper.getAirport(booking.getToAP());
			JLabel lblTodetails = new JLabel(toap.getAirportName());
			contentPanel.add(lblTodetails, "cell 1 1");
		}
		{
			JLabel lblDepartureDate = new JLabel("Departure Date");
			contentPanel.add(lblDepartureDate, "cell 0 2");
		}
		{
			JLabel lbldepDate = new JLabel(booking.getStdate());
			contentPanel.add(lbldepDate, "flowx,cell 1 2");
		}
		{
			JLabel lblPrice = new JLabel("price");
			contentPanel.add(lblPrice, "cell 0 3");
		}
		{
			lblTotalprice = new JLabel(String.format("$%.2f", booking.getPrice()));
			contentPanel.add(lblTotalprice, "cell 1 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("cancel");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						if(!helper.deleteBookingSchedule(booking.getOrderNumber())){
							del_filed("deleted failed, please refresh booking list");
							return;
						}
						if(helper.deleteBooking(booking.getUser_id(), booking.getOrderNumber())){
							del_success();
							BookingDetailsDialog.this.dispose();
							listener.orderfresh();
							return;
						}else{
							del_filed("deleted failed, please refresh booking list");
							return;
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("close");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						BookingDetailsDialog.this.dispose();
					}
				});
			}
		}
		helper=DataStorage.dStorage.helper;
		ArrayList<ScheduleBooking> list = helper.getScheduleBooking(booking.getOrderNumber());
		model = new SchBookTableModel(list);
		{
			JLabel lblPatmentMtehod = new JLabel("patment mtehod");
			contentPanel.add(lblPatmentMtehod, "cell 0 4,alignx trailing");
		}
		{
			JLabel lblPayment = new JLabel(booking.getPayMethod());
			contentPanel.add(lblPayment, "cell 1 4");
		}
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, "cell 0 5 2 1,grow");
		scrollPane.setViewportView(table);
		
	}
	private void del_filed(String info){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(BookingDetailsDialog.this,
				info,
				"warring",
				JOptionPane.YES_OPTION,
				JOptionPane.ERROR_MESSAGE,
				null,options,options[0]);
	}
	private void del_success(){
		Object[] options={"sure"};
		JOptionPane.showOptionDialog(BookingDetailsDialog.this,
				"booking canceled",
				"success",
				JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
	}
	
	
	public interface BookingdDelete{
		public void orderfresh();
	}
}
