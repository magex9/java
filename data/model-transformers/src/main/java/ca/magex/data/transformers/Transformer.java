package ca.magex.data.transformers;

public interface Transformer<DECODED extends Object, ENCODED extends Object> {

	public ENCODED encode(DECODED obj);
	
	public DECODED decode(ENCODED input);
	
}
