package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.HAH.jdbc.model.configuration.AppConfig;
import com.HAH.jdbc.model.dto.Category;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class JdbcTest {

	@Autowired
	JdbcOperations jdbc;

	@Test
	@Order(1)
	void test1() {
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] { "Foods" });
		params.add(new Object[] { "Drinks" });
		params.add(new Object[] { "Sports" });
		params.add(new Object[] { "Gym Accessories" });
		params.add(new Object[] { "Health Accessories" });
		var count = jdbc.batchUpdate("insert into category (name) values(?)", params);
		assertEquals(5, count.length);
		System.out.println(params);
	}

	@Test
	@Order(2)
	void test2() {
		var list = jdbc.query("select * from category", new BeanPropertyRowMapper<Category>(Category.class));
		System.out.println(list);
	}
}
