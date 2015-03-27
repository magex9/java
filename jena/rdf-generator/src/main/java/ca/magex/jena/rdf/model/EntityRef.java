package ca.magex.jena.rdf.model;

import org.apache.commons.codec.digest.DigestUtils;

public class EntityRef implements Subject, Value {

	public static final String PREFIX = "entity:";
	
	public String key;
	
	public EntityRef(Type type, String ref) {
		this.key = buildKey(type, ref);
	}
	
	public EntityRef(String domain, String type, String ref) {
		this.key = buildKey(new Type(domain, type), ref);
	}

	public static String buildKey(Type type, String ref) {
		return DigestUtils.md5Hex(type.toString() + "/" + ref).substring(0, 10);
	}
	
	@Override
	public String toString() {
		return PREFIX + key;
	}
	
}
