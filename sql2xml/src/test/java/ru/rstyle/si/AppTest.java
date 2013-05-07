package ru.rstyle.si;

import ru.rstyle.so.main.Main;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest  extends TestCase
{
	
private static String sqlQuery = "SELECT id, " +
			            "XMLELEMENT(name document, " +
			                               "XMLATTRIBUTES(caption, description), " +
			                               "XMLELEMENT(name mnemo, mnemo)) " +
			        "FROM sys_classes " +
			      "WHERE id = (SELECT id FROM sys_classes  WHERE dataset = 'r2_doc') ";
	
    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
    	boolean result = Main.main(sqlQuery);
    	
    	assertTrue("The results are noy identical!", result);
    	
        return new TestSuite( AppTest.class );
    }


    public void testApp()
    {
        assertTrue( true );
    }
}
