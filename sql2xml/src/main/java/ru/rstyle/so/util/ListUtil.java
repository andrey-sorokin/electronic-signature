package ru.rstyle.so.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ListUtil.class.getName());

	public static boolean compare(List<String> args) {
		for (int i = 1; i < args.size(); i++) {
			boolean result = args.get(i - 1).equals(args.get(i));
			if (!result) {
				return false;
			}
		}

		return true;

	}

	public static void print(List<String> list) {
		for (String sql : list) {
			LOG.info(sql);
		}
	}

}
