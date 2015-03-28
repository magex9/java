package ca.magex.data.transformers;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

public class XmlStringTransformer<POJO extends Object> extends AbstractTransformer<POJO, String> {

	public String encode(POJO obj) {
		Writer writer = new StringWriter();
		try {
			org.exolab.castor.xml.Marshaller.marshal(obj, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public POJO decode(String input) {
		try {
			String xml = input.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			InputSource inputSource = new InputSource(new StringReader(xml));
			return (POJO) Unmarshaller.unmarshal(getDecodedClass(), inputSource);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
