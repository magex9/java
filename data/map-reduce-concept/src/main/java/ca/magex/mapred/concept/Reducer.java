package ca.magex.mapred.concept;

import java.util.Collection;

public interface Reducer {

	public void reduce(String key, Collection<Object> values, Context context);
	
}
