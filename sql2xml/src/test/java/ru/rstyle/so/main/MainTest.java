package ru.rstyle.so.main;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: victor.alenkov
 * Date: 13.05.13
 * Time: 18:35
 */
public class MainTest {
	private static String sqlQuery = "SELECT id, " +
	                                 "XMLELEMENT(name document, " +
	                                 "XMLATTRIBUTES(caption, description), " +
	                                 "XMLELEMENT(name mnemo, mnemo)) " +
	                                 "FROM sys_classes " +
	                                 "WHERE id = (SELECT id FROM sys_classes  WHERE dataset = 'r2_doc') ";

	@Test(enabled = true)
	public void testMain() throws Exception {
		Assert.assertTrue(Main.main(sqlQuery));
	}
}
