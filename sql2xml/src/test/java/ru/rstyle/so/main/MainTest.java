package ru.rstyle.so.main;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {
	private static String sqlQuery = "SELECT id, " +
	                                 "XMLELEMENT(name document, " +
	                                 "XMLATTRIBUTES(caption, description), " +
	                                 "XMLELEMENT(name mnemo, mnemo)) " +
	                                 "FROM sys_classes " +
	                                 "WHERE id = (SELECT id FROM sys_classes  WHERE dataset = 'r2_doc') ";

	@Test(enabled = false)
	public void testMain() throws Exception {
		Assert.assertTrue(Main.main(sqlQuery));
	}
}
