package airline;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

import models.AirlineMileage;
import models.UserInfo;

public class MileageDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4696839352116755658L;
	private JTable table;


	public MileageDialog(UserInfo info) {
		setBounds(100, 100, 450, 300);
		setModal(true);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		ArrayList<AirlineMileage> list = DataStorage.dStorage.helper.getMileage(info);
		AirlineMileageTableModel model = new AirlineMileageTableModel(list);
		table = new JTable(model);
		scrollPane.setViewportView(table);
	}

}
