package ca.magex.mapred.concept;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class Context {

	private String inputDir;
	
	private String outputDir;
	
	private Class<? extends Mapper> mapper;
	
	private Class<? extends Reducer> reducer;
	
	private Map<String, Collection<Object>> data;
	
	public Context() {
		this.data = new HashMap<String, Collection<Object>>();
	}
	
	public void emit(String key, String value) {
		if (!data.containsKey(key))
			data.put(key, new ArrayList<Object>());
		data.get(key).add(value);
	}
	
	public Set<String> getKeys() {
		return data.keySet();
	}
	
	public Collection<Object> getValues(String key) {
		return data.get(key);
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public Class<? extends Mapper> getMapper() {
		return mapper;
	}

	public void setMapper(Class<? extends Mapper> mapper) {
		this.mapper = mapper;
	}

	public Class<? extends Reducer> getReducer() {
		return reducer;
	}

	public void setReducer(Class<? extends Reducer> reducer) {
		this.reducer = reducer;
	}

	public void run() throws Exception {
		for (File file : new File(this.getInputDir()).listFiles()) {
			String content = FileUtils.readFileToString(file);
			Mapper mapper = this.getMapper().newInstance();
			mapper.map(file.getAbsolutePath(), content, this);
		}
		
		for (String key : this.getKeys()) {
			Reducer reducer = this.getReducer().newInstance();
			reducer.reduce(key, this.getValues(key), this);
		}
	}
	
}
