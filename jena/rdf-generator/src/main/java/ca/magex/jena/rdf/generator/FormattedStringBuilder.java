package ca.magex.jena.rdf.generator;

public class FormattedStringBuilder {

	private StringBuilder sb;
	
	private String prefix = "";
	
	public FormattedStringBuilder() {
		this.sb = new StringBuilder();
	}
	
	public void append(String msg) {
		sb.append(prefix);
		sb.append(msg);
		sb.append("\n");
	}
	
	public void indent(String msg) {
		append(msg);
		prefix = prefix + "\t";
	}
	
	public void unindent(String msg) {
		prefix = prefix.substring(1);
		append(msg);
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
	
}
