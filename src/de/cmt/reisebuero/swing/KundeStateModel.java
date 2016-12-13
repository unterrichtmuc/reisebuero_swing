package de.cmt.reisebuero.swing;

public class KundeStateModel {

	private int value;
	
	private String text;

	public KundeStateModel(int value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
