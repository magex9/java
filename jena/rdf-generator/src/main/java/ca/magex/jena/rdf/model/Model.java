package ca.magex.jena.rdf.model;

import java.util.List;

public class Model {

	private List<Triple> triples;
	
	public List<Triple> getTriples() {
		return triples;
	}
	
	public void setTriples(List<Triple> triples) {
		this.triples = triples;
	}

}
