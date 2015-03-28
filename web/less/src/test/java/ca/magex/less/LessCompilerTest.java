package ca.magex.less;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import ca.magex.less.LessCompiler;

public class LessCompilerTest extends TestCase {
	
	@Test
	public void testDataUri() throws Exception {
		String css = compile("data-uri");
		System.out.println(css);
		assertEquals("body{background-color:#f00}div{background-color:#f33}", css);
	}
	
	@Test
	public void testVariables() throws Exception {
		String css = compile("variables");
		assertEquals("body{background-color:#f00}div{background-color:#f33}", css);
	}
	
	private String compile(String filename) throws Exception {
		File less = new File("src/test/resources/" + filename + ".less");
		return LessCompiler.compile(less);
	}
	
}
