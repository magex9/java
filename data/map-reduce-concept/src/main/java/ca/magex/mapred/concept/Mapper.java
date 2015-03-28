package ca.magex.mapred.concept;

public interface Mapper {

	public void map(String id, String contents, Context context);
	
}
