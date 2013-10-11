package edu.oregonstate.cope.clientRecorder;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientRecorderTest {
	/* Text Change Tests */
	@Test(expected = RuntimeException.class)
	public void testRecordTextChangeNull() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		cr.buildJSONTextChange(null, 0, 0, null, null, null);
	}

	@Test(expected = RuntimeException.class)
	public void testRecordTextChangeNoSourceFile() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		cr.buildJSONTextChange("", 0, 0, "", "", "");
	}

	@Test(expected = RuntimeException.class)
	public void testRecordTextChangeNoOrigin() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		cr.buildJSONTextChange("", 0, 0, "/sampleFile", "", "");
	}

	@Test
	public void testRecordTextChangeNoOp() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		JSONObject result1 = cr.buildJSONTextChange("", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		JSONObject obj = createChangeJSON("", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		assertEquals(result1, obj);
	}

	@Test
	public void testRecordTextChangeAdd() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		JSONObject result1 = cr.buildJSONTextChange("addedText", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		JSONObject obj = createChangeJSON("addedText", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		assertEquals(result1, obj);
	}

	@Test
	public void testRecordTextChangeDelete() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		JSONObject result1 = cr.buildJSONTextChange("", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		JSONObject obj = createChangeJSON("", 0, 0, "/sampleFile", "changeOrigin", "IDEA");
		assertEquals(result1, obj);
	}

	@Test
	public void testRecordTextChangeReplace() throws Exception {
		ClientRecorder cr = new ClientRecorder();
		JSONObject result1 = cr.buildJSONTextChange("addedText", 3, 0, "/sampleFile", "changeOrigin", "IDEA");
		JSONObject obj = createChangeJSON("addedText", 3, 0, "/sampleFile", "changeOrigin", "IDEA");
		assertEquals(result1, obj);
	}

	private JSONObject createChangeJSON(String text, int offset, int length, String sourceFile, String changeOrigin, String IDE) {
		JSONObject j = new JSONObject();
		j.put("type", "Text");
		j.put("text", text);
		j.put("offset", offset);
		j.put("len", length);
		j.put("sourceFile", sourceFile);
		j.put("changeOrigin", changeOrigin);
		j.put("ide", IDE);
		return j;
	}

	/* Test Run Tests */

	  @Test(expected = RuntimeException.class) public void testRunNull() throws
	  Exception { ClientRecorder cr = new ClientRecorder();
	  cr.buildJSONIDEEvent(null, null, null); }


}
