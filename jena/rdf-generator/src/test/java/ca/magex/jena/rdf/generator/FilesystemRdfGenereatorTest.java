package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class FilesystemRdfGenereatorTest extends TestCase {

	@Test
	public void testProjectFiles() throws Exception {
		String output = "target/src-files.ttl.gz";
		new FilesystemRdfGenerator().generate(new File("src"), new File(output));
		GZIPInputStream is = new GZIPInputStream(new FileInputStream(new File(output)));
		String content = IOUtils.toString(is, "UTF-8"); 
		assertTrue(content.contains("TurtleModelManager.java"));
		System.out.println(content);
	}
	
}
