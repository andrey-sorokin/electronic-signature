package ru.rstyle.so.util;

import javax.sql.DataSource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import ru.rstyle.so.domain.XMLHolder;
import ru.rstyle.so.mapper.XMLMapper;

public class SpringUtil {

	private static AbstractApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("spring.cfg.xml");
	}

	public static XMLHolder queryForObject(String sqlQuery) {

		DataSource dataSource = (DataSource) context.getBean("dataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		XMLHolder xmlHolder = jdbcTemplate.queryForObject(sqlQuery,
				new Object[] {}, new XMLMapper());

		return xmlHolder;
	}

}
