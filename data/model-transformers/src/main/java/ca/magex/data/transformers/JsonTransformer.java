package ca.magex.data.transformers;

import net.sf.json.JSONObject;

public class JsonTransformer<POJO extends Object> extends AbstractTransformer<POJO, JSONObject> {
	
	public JSONObject encode(POJO obj) {
		return JSONObject.fromObject(obj);
	}
	
	@SuppressWarnings("unchecked")
	public POJO decode(JSONObject json) {
		return (POJO) JSONObject.toBean(json, getDecodedClass());
	}
	
}
