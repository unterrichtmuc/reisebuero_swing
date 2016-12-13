package de.cmt.reisebuero.swing;

import javax.swing.JTextField;

public class JValidatedTextField extends JTextField {
	
	public JValidatedTextField() {
		super();
		
		
	}
	
	
	@Override
	public String getText() {
		String txt = super.getText();
		
		if (txt.length() < 2) {
			try {
				throw new Exception("bla");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// TODO Auto-generated method stub
		return txt;
	}
}
