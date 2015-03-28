package ca.magex.data.transformers;

import net.sf.json.JSONObject;

public class Md5Transformer<POJO extends Object> extends AbstractTransformer<POJO, String> {
	
	public String encode(POJO obj) {
		return JSONObject.fromObject(obj).toString();
	}
	
	public POJO decode(String json) {
		throw new RuntimeException("No decode enabled");
	}
	
}
