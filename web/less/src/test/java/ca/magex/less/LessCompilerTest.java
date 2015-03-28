package ca.magex.less;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import ca.magex.less.LessCompiler;

public class LessCompilerTest extends TestCase {
	
	@Test
	public void testDataUri() throws Exception {
		String css = compile("data-uri");
		assertEquals(".avatar{background-image:data-uri('image/png;base64','bat-tux.png')}", css);
	}
	
	@Test
	public void testVariables() throws Exception {
		String css = compile("variables");
		assertEquals("body{margin:25px}", css);
	}
	
	@Test
	public void testEBorders() throws Exception {
		String css = compile("borders");
		assertEquals("#header{border-radius:5px;" + 
				"-webkit-border-radius:5px;" + 
				"-moz-border-radius:5px}" + 
				"#footer{border-radius:10px;" + 
				"-webkit-border-radius:10px;" + 
				"-moz-border-radius:10px}", css);
	}

	@Test
	public void testContects() throws Exception {
		String css = compile("contexts");
		assertEquals("#header h1{font-size:26px;" + 
				"font-weight:bold}" + 
				"#header p{font-size:12px}" + 
				"#header p a{text-decoration:none}" + 
				"#header p a:hover{border-width:1px}", css);
	}
	
	private String compile(String filename) throws Exception {
		File less = new File("src/test/resources/" + filename + ".less");
		return LessCompiler.compile(less);
	}
	
}
