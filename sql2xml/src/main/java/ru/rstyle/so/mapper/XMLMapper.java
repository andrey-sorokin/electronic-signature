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

        if (Semaphore.getVendor().equals("db2")) {
            holder.setXml(getXML(rs).replaceAll("&#xD;", ""));
        } else {
            holder.setXml(getXML(rs));
        }

        return holder;
    }

    private String getXML(ResultSet rs) throws SQLException {
        SQLXML sqlxml = rs.getSQLXML(2);
        String outcome = sqlxml.getString();

        return outcome;
    }
}