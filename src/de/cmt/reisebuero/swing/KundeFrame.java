package de.cmt.reisebuero.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.cmt.reisebuero.core.exception.InvalidAttributeValueException;
import de.cmt.reisebuero.core.kunde.Kunde;
import de.cmt.reisebuero.core.kunde.KundeSqlHelper;
import de.cmt.reisebuero.swing.db.DbHelper;

public class KundeFrame {

	private JFrame frmNeuerKunde;
	private JTextField textVorname;
	private JTextField textNachname;
	private JTextField textBenutzername;
	private JTextField textPasswort;
	private JTextField textPlz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KundeFrame window = new KundeFrame();
					window.frmNeuerKunde.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the application.
	 */
	public KundeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNeuerKunde = new JFrame();
		frmNeuerKunde.setResizable(false);
		frmNeuerKunde.setTitle("Neuer Kunde");
		frmNeuerKunde.setBounds(100, 100, 413, 389);
		frmNeuerKunde.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Speichern");

		btnNewButton.setBounds(274, 316, 113, 23);
		frmNeuerKunde.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Zur\u00FCcksetzen");

		btnClear.setBounds(38, 316, 131, 23);
		frmNeuerKunde.getContentPane().add(btnClear);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(10, 14, 46, 14);
		frmNeuerKunde.getContentPane().add(lblVorname);
		
		textVorname = new JTextField();
		textVorname.setBounds(89, 11, 298, 20);
		frmNeuerKunde.getContentPane().add(textVorname);
		textVorname.setColumns(10);
		
		textNachname = new JTextField();
		textNachname.setColumns(10);
		textNachname.setBounds(89, 42, 298, 20);
		frmNeuerKunde.getContentPane().add(textNachname);
		
		JLabel lblNachname = new JLabel("Nachname");
		lblNachname.setBounds(10, 49, 69, 14);
		frmNeuerKunde.getContentPane().add(lblNachname);
		
		textBenutzername = new JTextField();
		textBenutzername.setColumns(10);
		textBenutzername.setBounds(89, 74, 298, 20);
		frmNeuerKunde.getContentPane().add(textBenutzername);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBounds(10, 81, 69, 14);
		frmNeuerKunde.getContentPane().add(lblBenutzername);
		
		textPasswort = new JTextField();
		textPasswort.setColumns(10);
		textPasswort.setBounds(89, 105, 298, 20);
		frmNeuerKunde.getContentPane().add(textPasswort);
		
		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setBounds(10, 106, 69, 14);
		frmNeuerKunde.getContentPane().add(lblPasswort);
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum");
		lblGeburtsdatum.setBounds(10, 137, 69, 14);
		frmNeuerKunde.getContentPane().add(lblGeburtsdatum);
		
		JFormattedTextField textGeburtsdatum = new JFormattedTextField();
		textGeburtsdatum.setText("01.01.1980");
		textGeburtsdatum.setBounds(89, 134, 298, 20);
		frmNeuerKunde.getContentPane().add(textGeburtsdatum);
		
		JLabel lblPlz = new JLabel("Status:");
		lblPlz.setBounds(10, 204, 46, 14);
		frmNeuerKunde.getContentPane().add(lblPlz);
		
		textPlz = new JTextField();
		textPlz.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String text = textPlz.getText();
				
				try
				{
					int test = Integer.valueOf(text);				
				} catch (Exception e) {
					textPlz.setText("");
				}
				
				
				if (text.length() > 5) {
					textPlz.setText(text.substring(0, 5));
				}				
			}
		});
		textPlz.setColumns(10);
		textPlz.setBounds(89, 170, 298, 20);
		frmNeuerKunde.getContentPane().add(textPlz);
		
		JComboBox selectStatus = new JComboBox();
		
		KundeStateModel aktiv = new KundeStateModel(1, "Aktiv");
		KundeStateModel inaktiv = new KundeStateModel(2, "Inaktiv");
		KundeStateModel archiviert = new KundeStateModel(3, "Archiviert");
		KundeStateModel geloescht = new KundeStateModel(4, "Geloescht");
		
		selectStatus.setModel(new DefaultComboBoxModel(new Object[] {aktiv, inaktiv, archiviert, geloescht}));
		selectStatus.setBounds(89, 201, 288, 20);
		frmNeuerKunde.getContentPane().add(selectStatus);
		
		JLabel label = new JLabel("PLZ:");
		label.setBounds(10, 173, 46, 14);
		frmNeuerKunde.getContentPane().add(label);
		
		JButton button = new JButton("Abbrechen");
		button.setBounds(179, 316, 85, 23);
		frmNeuerKunde.getContentPane().add(button);
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
	

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset(textGeburtsdatum, selectStatus);
			}

			
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// @todo Validierung!				
				
				String nachname = textNachname.getText();
				
				Kunde k = null;

				try {
					k = new Kunde(nachname);
				} catch (InvalidAttributeValueException e2) {
					JOptionPane.showMessageDialog(frmNeuerKunde, "Zu kurzer Nachname", "Fehler", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				k.setVorname(textVorname.getText());
				k.setBenutzername(textBenutzername.getText());
				
				String geburtsDatum = textGeburtsdatum.getText();
								
				try {
					SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
					
					if (geburtsDatum.isEmpty())
					{
						k.setGeburtsdatum(sf.parse("00.00.0000")); // 0000-00-00 00:00:00
					}
					else
					{					
						k.setGeburtsdatum(sf.parse(geburtsDatum));
					}					
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frmNeuerKunde, "Ungültiges Geburtsdatum!", "Fehler", JOptionPane.ERROR_MESSAGE);
					
					return;
				}				
				
				k.setPassword(textPasswort.getText());
				k.setPlz(textPlz.getText());
				
				KundeStateModel ksm = (KundeStateModel) selectStatus.getSelectedItem();
				k.setState(ksm.getValue());
				
				Connection con = DbHelper.get();
				
				try {
					KundeSqlHelper.create(con, k);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Creating customer failed");

					return;
				}
				
				reset(textGeburtsdatum, selectStatus);
				frmNeuerKunde.setVisible(false);
			}
		});
	}

	private void reset(JFormattedTextField textGeburtsdatum, JComboBox selectStatus) {
		textVorname.setText("");
		textNachname.setText("");
		textBenutzername.setText("");
		textPasswort.setText("");
		textPlz.setText("");
		textGeburtsdatum.setText("01.01.1980");
		selectStatus.setSelectedItem(1);
	}
	
	public JFrame getFrmNeuerKunde() {
		return frmNeuerKunde;
	}

	public void setFrmNeuerKunde(JFrame frmNeuerKunde) {
		this.frmNeuerKunde = frmNeuerKunde;
	}
}
