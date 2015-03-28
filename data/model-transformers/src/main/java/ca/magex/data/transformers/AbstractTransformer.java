package ca.magex.data.transformers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractTransformer<DECODED extends Object, ENCODED extends Object>
		implements Transformer<DECODED, ENCODED> {

	private Class<DECODED> decodedClass;

	private Class<ENCODED> encodedClass;

	@SuppressWarnings("unchecked")
	public AbstractTransformer() {
		try {
			ParameterizedType parameterizedType = ((ParameterizedType) getClass()
					.getGenericSuperclass());
			Type[] types = parameterizedType.getActualTypeArguments();
			decodedClass = (Class<DECODED>) types[0];
			encodedClass = (Class<ENCODED>) types[1];
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Class<DECODED> getDecodedClass() {
		return decodedClass;
	}
	
	public Class<ENCODED> getEncodedClass() {
		return encodedClass;
	}

}
