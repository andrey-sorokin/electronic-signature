package ru.rstyle.so.transform;

public class Oracle {
	public static String getAsClob(String input) {
		return input.replaceAll("(?i)(XMLELEMENT.*?)\\sFROM", "TO_CLOB($1) FROM");
	}

}