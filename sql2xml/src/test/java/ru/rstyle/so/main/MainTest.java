package ru.rstyle.so.main;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {
	
	@Test(enabled = true)
	public void testMain() throws Exception {
		
		String  [] sqlQuery  = new String [4];
		
		// test upper case + XMLFOREST function
		sqlQuery[0] = "SELECT id, " +
                "XMLELEMENT(name document, " +
                "XMLFOREST(s.caption as \"Caption\", " +
                "s.description as \"Description\")) " + 
                "FROM sys_classes s " +
                "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc')";
		
        // test lower case + XMLATTRIBUTES element
		sqlQuery[1] = "select id, " +
                "xmlelement(name document, " +
                "xmlattributes(caption, description), " +
                "xmlelement(name mnemo, mnemo)) " +
                "from sys_classes " +
                "where id = (select id from sys_classes  where dataset = 'r2_doc') ";
		
		// test XMLCONCAT function
		sqlQuery[2] =  "SELECT id, " +
		        " xmlelement(name root, " + 
                        "XMLCONCAT(XMLELEMENT(NAME \"caption\", s.caption), " +
                                  "XMLELEMENT(NAME \"description\", s.description))) " +
               "FROM sys_classes s " +
               "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc') ";
		
		// test XMLAGG function
		sqlQuery[3] = "SELECT 1, " +
				"XMLELEMENT(NAME \"root\", " +
                      "XMLATTRIBUTES(s.name AS \"name\"), " +
                  " XMLAGG(XMLELEMENT(NAME \"description\", s.description))) " +
                  " FROM sys_classes s " +
                  " GROUP BY 1, name " +
                  " having s.name = 'Контакт'";
		
		for (String sql: sqlQuery){
		     Assert.assertTrue(Main.main(sql, true));
		}
	}
}
