package ru.rstyle.so.main;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import ru.rstyle.so.domain.Semaphore;
import ru.rstyle.so.domain.XMLHolder;
import ru.rstyle.so.transform.Oracle;
import ru.rstyle.so.util.ListUtil;
import ru.rstyle.so.util.SpringUtil;
import ru.rstyle.so.util.XMLUtil;

public class Main {

	public static boolean main(String sqlQuery) {
		

		List<String> xml = new ArrayList<String>();

		for (Vendors vendor : Vendors.values()) {

			Semaphore.vendor = vendor.toString();

			XMLHolder xmlHolder = SpringUtil.queryForObject(getSqlByVendor(
					Semaphore.vendor, sqlQuery));

			Document doc = XMLUtil.getDOM(xmlHolder.getXml());

			doc = XMLUtil.toLowerCase(doc);

			xml.add(XMLUtil.toString(doc));
		}
		
		ListUtil.print(xml);
		
		return ListUtil.compare(xml);
		
	}

	public enum Vendors {
		oracle, db2, postresql
	}

	private static String getSqlByVendor(String vendor, String sqlQuery) {

		if (vendor.equals("oracle")) {
			return sqlQuery = Oracle.getAsClob(sqlQuery);
		}
		return sqlQuery;

	}

}
