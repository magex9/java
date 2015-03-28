package ca.magex.mapred.tags;

import org.apache.log4j.Logger;

import ca.magex.mapred.concept.Context;
import ca.magex.mapred.concept.Mapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TagMapper implements Mapper {

	private static Logger logger = Logger.getLogger(TagMapper.class);
	
	public void map(String docId, String contents, Context context) {
		try {
			JSONObject json = JSONObject.fromObject(contents);
			logger.info("Parsed: " + docId);
			if (json.get("tags") instanceof JSONArray) {
				for (int i = 0; i < json.getJSONArray("tags").size(); i++) {
					String tag = json.getJSONArray("tags").getString(i);
					logger.info("Emitting: " + tag + " for " + json);
					context.emit(tag, json.toString());
				}
			} else {
				context.emit("none", json.toString());
			}
		} catch (Exception e) {
			logger.info("Skipping: " + docId);
		}
	}
	
}
