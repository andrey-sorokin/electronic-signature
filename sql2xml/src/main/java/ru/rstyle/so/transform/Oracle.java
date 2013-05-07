package ru.rstyle.so.transform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Oracle {
	public static String getAsClob(String input) {

		StringBuffer sb = new StringBuffer();

		Pattern p = Pattern.compile("XMLELEMENT.*?FROM");
		Matcher m = p.matcher(input);

		while (m.find()) {
			m.appendReplacement(sb, getReplacement(m.group()));
		}

		m.appendTail(sb);

		return sb.toString();
	}

	private static String getReplacement(String input) {
		return "TO_CLOB(" + input.replace("FROM", "") + ")" + " FROM ";
	}
}