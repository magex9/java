package ca.magex.jena.rdf.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ca.magex.jena.rdf.model.Model;

public interface ModelManager {

	public void read(Model model, InputStream is) throws IOException;
	
	public void write(Model model, OutputStream os) throws IOException;
	
}
