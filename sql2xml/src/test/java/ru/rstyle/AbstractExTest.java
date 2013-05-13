package ru.rstyle;

import junit.framework.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: victor.alenkov
 * Date: 13.05.13
 * Time: 19:35
 */
abstract public class AbstractExTest {
	@DataProvider(name = "test")
	public String[][] dTest() {
		return getStrings();
	}

	protected String[][] getStrings() {
		return new String[][]{};
	}

	@Test(dataProvider = "test")
	public void test(String s) throws Exception {
		Assert.assertNotNull(s);
	}
}
