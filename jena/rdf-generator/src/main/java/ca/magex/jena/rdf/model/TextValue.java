package ca.magex.jena.rdf.model;

public class TextValue implements Value {

	private String text;
	
	public TextValue(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "'" + text + "'";
	}
	
}
