package ca.magex.mapred.tags;

import junit.framework.TestCase;

import org.junit.Test;

import ca.magex.mapred.concept.Context;

public class FindTagsTest extends TestCase {

	@Test
	public void testFindTags() throws Exception {
		Context context = new Context();
		context.setInputDir("src/test/resources/tags");
		context.setOutputDir("target/tags/findTags");
		context.setMapper(TagMapper.class);
		context.setReducer(TagReducer.class);
		context.run();
		
		assertTrue(context.getKeys().contains("cycling"));
		assertTrue(context.getKeys().contains("developer"));
		assertFalse(context.getKeys().contains("type"));
		assertFalse(context.getKeys().contains("Scott"));
		
		assertEquals(3, context.getKeys().size());
		assertEquals(2, context.getValues("cycling").size());
		assertEquals(1, context.getValues("developer").size());
		assertEquals(1, context.getValues("none").size());
	}
	
}
