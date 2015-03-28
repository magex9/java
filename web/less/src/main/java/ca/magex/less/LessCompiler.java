package ca.magex.less;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.asual.lesscss.LessEngine;

public class LessCompiler {

	public static String compile(File input) throws Exception {
		String less = FileUtils.readFileToString(input);
		LessEngine engine = new LessEngine();
		return engine.compile(less, true);
	}
	
	public static void compile(File input, File output) throws Exception {
		String less = FileUtils.readFileToString(input);
		LessEngine engine = new LessEngine();
		String css = engine.compile(less.toString());
		FileUtils.writeStringToFile(output, css);
	}
	
}
