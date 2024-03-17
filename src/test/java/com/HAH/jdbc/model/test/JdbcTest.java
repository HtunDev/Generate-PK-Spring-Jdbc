package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.HAH.jdbc.model.configuration.AppConfig;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class JdbcTest {

	@Autowired
	JdbcOperations jdbc;

	@Test
	void test1() {
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] { 1, "Foods" });
		params.add(new Object[] { 2, "Drinks" });
		params.add(new Object[] { 3, "Sports" });
		params.add(new Object[] { 4, "Gym Accessories" });
		params.add(new Object[] { 5, "Health Accessories" });
		var count = jdbc.batchUpdate("insert into category values(?,?)", params);
		assertEquals(5, count.length);
		System.out.println(params);
	}
}
