package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import DB.CommInterface;
import DB.ConnectionManager;
import DB.Util;

public class MainWindow {

	private JFrame frame;
	public Boolean loggedIn = false;
	private JTable filmTable;
	public JTable storeTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		ConnectionManager.Reg();
		ConnectionManager.Connect();


		LoginDialog login = new LoginDialog(frame);
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		login.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		//TABBEDPANE
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(2, 0, 550, 470);
		frame.getContentPane().add(tabbedPane);
		
		//ImageIcon icon = Util.createImageIcon("disc.png");


		//FILMTAB
		
		JPanel filmTab = new JPanel();
		tabbedPane.addTab("Films", null, filmTab, null);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		filmTab.setLayout(null);
		
		JScrollPane filmScrollPane = new JScrollPane();
		filmScrollPane.setBounds(5, 5, 540, 330);
		filmTab.add(filmScrollPane);
		
		filmTable = new JTable(CommInterface.getAllFilmData());
		filmScrollPane.setViewportView(filmTable);

		
		JButton addFilmBtn = new JButton("Add Film");
		addFilmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFilmDialog addFDialog = new AddFilmDialog();
				addFDialog.setLocationRelativeTo(null);
				addFDialog.setVisible(true);
				addFDialog.addWindowListener(new WindowAdapter() {
				
					@Override
					public void windowClosed(WindowEvent arg0) {
						filmTable.setModel(CommInterface.getAllFilmData());						
					}
					
				});
			}
		});
		addFilmBtn.setBounds(12, 381, 89, 23);
		filmTab.add(addFilmBtn);

		JButton LPFilmBtn = new JButton("Cheap films");
		LPFilmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetLowPriceFilmDialog LPFdialog = new GetLowPriceFilmDialog();
				LPFdialog.setLocationRelativeTo(null);
				LPFdialog.setVisible(true);
				LPFdialog.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent arg0) {
						filmTable.setModel(CommInterface.getAllFilmData());
					}

				});
			}
		});
		LPFilmBtn.setBounds(270, 381, 120, 23);
		filmTab.add(LPFilmBtn);
		
		JButton deleteFilmBtn = new JButton("Delete Film");
		deleteFilmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(filmTable.getSelectedRowCount() == 1) {
					int row = filmTable.getSelectedRow();
					CommInterface.DeleteFilm((int)filmTable.getValueAt(row, 0));
					filmTable.setModel(CommInterface.getAllFilmData());	
				}else {
					Util.Alert("Too many, or no rows are selected!");
				}
				
			}
		});
		deleteFilmBtn.setBounds(418, 381, 117, 23);
		filmTab.add(deleteFilmBtn);
		
		JButton modifyFilmBtn = new JButton("Modify Film");
		modifyFilmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(filmTable.getSelectedRowCount() == 1) {
					int row = filmTable.getSelectedRow();
					ModifyFilmDialog modDialog = new ModifyFilmDialog((int)filmTable.getValueAt(row, 0), (String)filmTable.getValueAt(row, 1), (String)filmTable.getValueAt(row, 2),(int)filmTable.getValueAt(row, 3), (int)filmTable.getValueAt(row, 4));
					modDialog.setLocationRelativeTo(null);
					modDialog.setVisible(true);
					modDialog.addWindowListener(new WindowAdapter() {
					
						@Override
						public void windowClosed(WindowEvent arg0) {
							filmTable.setModel(CommInterface.getAllFilmData());						
						}
						
					});
				}else {
					Util.Alert("Too many, or no rows are selected!");
				}
				
			}
		});
		modifyFilmBtn.setBounds(111, 381, 117, 23);
		filmTab.add(modifyFilmBtn);
		
		
		//STORETAB
		
		JPanel storeTab = new JPanel();
		tabbedPane.addTab("Stores", null, storeTab, null);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		storeTab.setLayout(null);
		
		JScrollPane storeScrollPane = new JScrollPane();
		storeScrollPane.setBounds(5, 5, 540, 330);
		storeTab.add(storeScrollPane);
		
		storeTable = new JTable(CommInterface.getAllStoreData());
		storeScrollPane.setViewportView(storeTable);
		
		JButton deleteStoreBtn = new JButton("Delete Store");
		deleteStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(storeTable.getSelectedRowCount() == 1) {
					int row = storeTable.getSelectedRow();
					CommInterface.DeleteStore((int)storeTable.getValueAt(row, 0));
					storeTable.setModel(CommInterface.getAllStoreData());	
					filmTable.setModel(CommInterface.getAllFilmData());
				}else {
					Util.Alert("Too many, or no rows are selected!");
				}
			}
		});
		deleteStoreBtn.setBounds(418, 392, 117, 23);
		storeTab.add(deleteStoreBtn);
		
		JButton addStoreBtn = new JButton("Add Store");
		addStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStoreDialog addSDialog = new AddStoreDialog();
				addSDialog.setLocationRelativeTo(null);
				addSDialog.setVisible(true);
				addSDialog.addWindowListener(new WindowAdapter() {
				
					@Override
					public void windowClosed(WindowEvent arg0) {
						storeTable.setModel(CommInterface.getAllStoreData());						
					}
					
				});
			}
		});
		addStoreBtn.setBounds(5, 392, 99, 23);
		storeTab.add(addStoreBtn);
		
		JButton modifyStoreBtn = new JButton("Modify Store");
		modifyStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(storeTable.getSelectedRowCount() == 1) {
					int row = storeTable.getSelectedRow();
					ModifyStoreDialog modDialog = new ModifyStoreDialog((int)storeTable.getValueAt(row, 0), (String)storeTable.getValueAt(row, 1), (String)storeTable.getValueAt(row, 2),(int)storeTable.getValueAt(row, 3));
					modDialog.setLocationRelativeTo(null);
					modDialog.setVisible(true);
					modDialog.addWindowListener(new WindowAdapter() {
					
						@Override
						public void windowClosed(WindowEvent arg0) {
							storeTable.setModel(CommInterface.getAllStoreData());
						}
						
					});
				}else {
					Util.Alert("Too many, or no rows are selected!");
				}
			}
		});
		modifyStoreBtn.setBounds(114, 392, 117, 23);
		storeTab.add(modifyStoreBtn);
		
		
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Main");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				ConnectionManager.Disconnect();
			}
		});
	
	}
}
