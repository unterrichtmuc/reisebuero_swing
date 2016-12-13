package de.cmt.reisebuero.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmReisebroManager;
	private JTable table;

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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
		panel.add(table);
		
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
				
				KundeFrame kf = new KundeFrame();
				kf.getFrmNeuerKunde().setVisible(true);
			}
		});
		mnKunde.add(mnitNeuKunde);
	}
}
