package ru.rstyle.so.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JVMUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(JVMUtil.class.getName());
	
	public static void showEncoding() {
		String encoding = new java.io.OutputStreamWriter(System.out)
				.getEncoding();
		
		LOG.info("Encoding : " + encoding);
		
	}
}
