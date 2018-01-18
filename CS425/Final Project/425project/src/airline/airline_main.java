package airline;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataBase.DataHelper;
import airline.LoginDialog.OnloginSuccess;
import airline.RegDialog.OnRegSuccess;
import airline.UserPanel.LogoutSuccess;

import javax.swing.JTabbedPane;


public class airline_main extends JFrame implements OnloginSuccess, OnRegSuccess,LogoutSuccess {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5188232338679987601L;
	private JPanel contentPane;
	JPanel panel,user_panel;
	DataHelper helper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					airline_main frame = new airline_main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public airline_main() {
		DataStorage.dStorage=new DataStorage();
		helper=new DataHelper();
		DataStorage.dStorage.helper=helper;
		DataStorage.dStorage.countries=helper.countries();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 1000);
		setResizable(false); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel info_panel = new InfoPanel();
		tabbedPane.addTab("InfoPanel", null, info_panel, null);
		
		user_panel = new JPanel();
		tabbedPane.addTab("user", null, user_panel, null);
		user_panel.setLayout(new BorderLayout(0, 0));
		
		
		panel = new LoginPanel(this);
		user_panel.add(panel, BorderLayout.CENTER);

	}

	@Override
	public void logined() {
		// TODO 自动生成的方法存根
		
		user_panel.remove(panel);
		panel= new UserPanel(this);
		user_panel.add(panel, BorderLayout.CENTER);
		
	}
	public void logouted(){
		user_panel.remove(panel);
		panel= new LoginPanel(this);
		user_panel.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void reged() {
		// TODO 自动生成的方法存根
		user_panel.remove(panel);
		panel= new UserPanel(this);
		user_panel.add(panel, BorderLayout.CENTER);
	}

}
