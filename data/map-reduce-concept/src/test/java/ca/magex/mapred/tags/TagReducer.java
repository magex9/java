package ca.magex.mapred.tags;

import java.io.File;
import java.util.Collection;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

import ca.magex.mapred.concept.Context;
import ca.magex.mapred.concept.Reducer;

public class TagReducer implements Reducer {

	public void reduce(String key, Collection<Object> values, Context context) {
		try {
			JSONObject results = new JSONObject();
			for (Object value : values) {
				JSONObject json = JSONObject.fromObject(value);
				results.put(value, json.getString("name"));
			}
			FileUtils.writeStringToFile(new File(context.getOutputDir(),  key), results.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
