package ru.rstyle.so.main;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {

    @Test(enabled = true)
    public void testMain() throws Exception {

        String[] sqlQuery = new String[7];

        // test upper case + XMLFOREST function
        sqlQuery[0] = "SELECT id, "
                + "XMLELEMENT(name document, "
                + "XMLFOREST(s.caption as \"Caption\", "
                + "s.description as \"Description\")) "
                + "FROM sys_classes s "
                + "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc')";

        // test lower case + XMLATTRIBUTES element
        sqlQuery[1] = "select id, "
                + "xmlelement(name document, "
                + "xmlattributes(caption, description), "
                + "xmlelement(name mnemo, mnemo)) "
                + "from sys_classes "
                + "where id = (select id from sys_classes  where dataset = 'r2_doc') ";

        // test XMLCONCAT function
        sqlQuery[2] = "SELECT id, "
                + "xmlelement(name root, "
                + "XMLCONCAT(XMLELEMENT(NAME \"caption\", s.caption), "
                + "XMLELEMENT(NAME \"description\", s.description))) "
                + "FROM sys_classes s "
                + "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc') ";

        // test XMLAGG function
        sqlQuery[3] = "SELECT 1, "
                + "XMLELEMENT(NAME \"root\", "
                + "XMLATTRIBUTES(s.name AS \"name\"), "
                + "XMLAGG(XMLELEMENT(NAME \"description\", s.description) ORDER BY description)) "
                + "FROM sys_classes s " + " GROUP BY 1, name "
                + "having s.name = 'Контакт'";

        // test XMLCOMMENT function
        sqlQuery[4] = "SELECT 1, "
                + "XMLELEMENT(NAME \"root\", "
                + "XMLATTRIBUTES(s.name AS \"name\"), "
                + "XMLCOMMENT('my comment'), " 
                + "XMLAGG(XMLELEMENT(NAME \"description\", s.description) ORDER BY description)) "
                + "FROM sys_classes s " + " GROUP BY 1, name "
                + "having s.name = 'Контакт'";
        // test XML processing instruction        
        sqlQuery[5] =  "SELECT 1," +
        		"XMLELEMENT(NAME \"root\", " +
        		" XMLPI ( "
                + "NAME \"Instruction\", 'Push the red button' "
                + ")) "
                + " FROM sys_classes "
                + "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc') ";
        // test XML namespace
        sqlQuery[6] =  "SELECT EMPNO, XMLELEMENT( "
                + "NAME \"adm:employee\", XMLNAMESPACES( "
                + " 'http://www.adm.com' AS \"adm\" " 
                + "),"
                + " XMLATTRIBUTES( "
                + " WORKDEPT AS \"adm:department\" "
                + "), "
                + "LASTNAME "
                + ") "
                + " FROM sys_classes "
                + "WHERE id = (SELECT id FROM sys_classes WHERE dataset = 'r2_doc') ";
        
        /*
         * sqlQuery[5] = "select 1, " + "xmlelement (name customer, " +
         * "xmlattributes(c.CustId as id), " +
         * "xmlforest (c.Name as name, c.City as city), " +
         * "xmlelement(name projects, " +
         * "(select xmlagg(xmlelement(name project, " +
         * "xmlattributes(p.ProjId as id), " + "xmlforest(p.Name as name))) " +
         * "from Projects p " + "where p.custid=c.custid ))) " +
         * "from Customers c";
         */
        
        for (String sql : sqlQuery) {
            Assert.assertTrue(Main.main(sql, true));
        }
    }
}
