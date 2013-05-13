package ru.rstyle.so.transform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Oracle {
	public static String getAsClob(String input) {

		StringBuffer sb = new StringBuffer();

		Pattern p = Pattern.compile("XMLELEMENT.*?FROM", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);

		while (m.find()) {
			m.appendReplacement(sb, getReplacement(m.group(0)));
		}

		m.appendTail(sb);

		return sb.toString();
	}

	private static String getReplacement(String input) {
		String result = "TO_CLOB(" + input.replaceAll("(?i)FROM", "") + ")" + " FROM ";
		return result;
	}
}