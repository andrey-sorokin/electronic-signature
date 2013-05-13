package ru.rstyle.so.transform;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: victor.alenkov
 * Date: 13.05.13
 * Time: 18:22
 */
public class OracleTest {
	@DataProvider(name = "sqls")
	public String[][] sqls() {
		final String[] sql0 = {"TO_CLOB(xmlelement(name document)) from", "xmlelement(name document) from"};
		final String[] sql1 = {"select deptid, TO_CLOB(xmlelement(name document, XMLFOREST(d.shortname as 'x', " +
		                       "d.name as 'y'))) from dept d WHERE d.deptid = (SELECT MAX(deptid) FROM dept)",
		                       "select deptid, xmlelement(name document, XMLFOREST(d.shortname as 'x', " +
		                       "d.name as 'y')) from dept d WHERE d.deptid = (SELECT MAX(deptid) FROM dept)"
		};
		final String[] sql2 = {"SELECT id, TO_CLOB(XMLELEMENT(name document, XMLATTRIBUTES(caption, description), " +
		                       "XMLELEMENT(name mnemo, mnemo))) FROM sys_classes WHERE id = (SELECT id FROM " +
		                       "sys_classes WHERE dataset = 'r2_doc')",
		                       "SELECT id, XMLELEMENT(name document, XMLATTRIBUTES(caption, description), " +
		                       "XMLELEMENT(name mnemo, mnemo)) FROM sys_classes WHERE id = (SELECT id FROM " +
		                       "sys_classes WHERE dataset = 'r2_doc')"
		};
		final String[] sql3 = {"SELECT TO_CLOB(xmlelement(NAME root, xmlelement(NAME mnemo, mnemo))) FROM sys_classes;",
		                       "SELECT xmlelement(NAME root, xmlelement(NAME mnemo, mnemo)) FROM sys_classes;"
		};
		return new String[][]{sql0, sql1, sql2, sql3};
	}

	@Test(dataProvider = "sqls")
	public void testGetAsClob(String sqlFinal, String sqlOrign) throws Exception {
		String sql = Oracle.getAsClob(sqlOrign).toLowerCase();
		Assert.assertEquals(sql, sqlFinal.toLowerCase());
	}
}
