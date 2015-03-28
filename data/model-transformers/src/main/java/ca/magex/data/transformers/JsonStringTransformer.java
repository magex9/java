package ca.magex.data.transformers;

import net.sf.json.JSONObject;

public class JsonStringTransformer<POJO extends Object> extends AbstractTransformer<POJO, String> {
	
	public String encode(POJO obj) {
		return JSONObject.fromObject(obj).toString(1);
	}
	
	@SuppressWarnings("unchecked")
	public POJO decode(String json) {
		return (POJO) JSONObject.toBean(JSONObject.fromObject(json), getDecodedClass());
	}
	
}
