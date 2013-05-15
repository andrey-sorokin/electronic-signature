package ru.rstyle.so.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

import ru.rstyle.so.domain.Semaphore;

import org.springframework.jdbc.core.RowMapper;

import ru.rstyle.so.domain.XMLHolder;

public class XMLMapper implements RowMapper<XMLHolder> {

	public XMLHolder mapRow(ResultSet rs, int rowNum) throws SQLException {

		XMLHolder holder = new XMLHolder();

		if (Semaphore.vendor.equals("oracle")) {
			holder.setXml(rs.getString(2));
		}

		else if (Semaphore.vendor.equals("db2")) {
			com.ibm.db2.jcc.DB2Xml data = (com.ibm.db2.jcc.DB2Xml) rs
					.getObject(2);
			String outcome = data.getDB2XmlString();
			outcome = outcome.replaceAll("&#xD;", "");
			holder.setXml(outcome);
		}

		else {
			SQLXML sqlxml = rs.getSQLXML(2);
			holder.setXml(sqlxml.getString());
		}

		

		return holder;
	}
}