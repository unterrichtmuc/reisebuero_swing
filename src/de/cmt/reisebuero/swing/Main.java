package de.cmt.reisebuero.swing;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.cmt.reisebuero.core.kunde.Kunde;
import de.cmt.reisebuero.core.kunde.KundeSqlHelper;
import de.cmt.reisebuero.swing.db.DbHelper;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private JFrame frmReisebroManager;
	private JTable table_kunden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmReisebroManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		loadKunden();
	}

	private void loadKunden() {
		Connection con = DbHelper.get();
		
		try {
			Kunde[] kunden = KundeSqlHelper.getKunden(con, KundeSqlHelper.ALL_KUNDEN);
			DefaultTableModel tm = new DefaultTableModel() {
				 public boolean isCellEditable(int row, int column) {
				        return false;
				 }
			};

			String[] columns = {"Id", "Vorname", "Nachname", "Benutzername", "Geburtsdatum", "Status"};
			
			Object[][] kundenData = new Object[kunden.length][6];
			SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
			
			for (int i = 0; i < kunden.length; i++)
			{
				// Der gesamte Kunde
				kundenData[i][0] = kunden[i];
				
				kundenData[i][1] = kunden[i].getVorname();
				kundenData[i][2] = kunden[i].getNachname();
				kundenData[i][3] = kunden[i].getBenutzername();
				kundenData[i][4] = sf.format(kunden[i].getGeburtsdatum());
				kundenData[i][5] = kunden[i].getState();
			}			
			
			tm.setDataVector(kundenData, columns);
			table_kunden.setModel(tm);
			table_kunden.repaint();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Loading failed");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReisebroManager = new JFrame();
		frmReisebroManager.setTitle("Reiseb\u00FCro Manager 0.9");
		frmReisebroManager.setBounds(100, 100, 628, 391);
		frmReisebroManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReisebroManager.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 21, 612, 331);
		frmReisebroManager.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kunden", null, panel, null);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table_kunden = new JTable();
		table_kunden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() < 2) {					
					return;
				}
				
				int zeile = table_kunden.getSelectedRow();
				System.out.println(zeile);
				
				// Kunde! 
				Kunde k = (Kunde) table_kunden.getModel().getValueAt(zeile, 0);
				
				System.out.println(k.getNachname());
				
				KundeFrame km = new KundeFrame(k);
				km.getFrmNeuerKunde().setVisible(true);
				
			}
		});
		table_kunden.setFillsViewportHeight(true);
		scrollPane.setRowHeaderView(table_kunden);
		table_kunden.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reisen", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Buchungen", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Zahlungen", null, panel_3, null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 612, 21);
		frmReisebroManager.getContentPane().add(menuBar);
		
		JMenu mnKunde = new JMenu("Kunde");
		menuBar.add(mnKunde);
		
		JMenuItem mnitNeuKunde = new JMenuItem("Neuer Kunde");
		mnitNeuKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Kunde anlegen");							
				
				// Singleton (Damit nur einmal geladen wird)
				if (kf == null) {
					 kf = new KundeFrame();
				}
								
				kf.getFrmNeuerKunde().setVisible(true);
			}
		});
		mnKunde.add(mnitNeuKunde);
	}
	
	KundeFrame kf = null;
}
