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

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(locations = "/application.xml")
public class JdbcTest {

	@Autowired
	JdbcOperations jdbc;

	@Test
	void test1() {
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] { 1, "Foods" });
		params.add(new Object[] { 2, "Drinks" });
		params.add(new Object[] { 3, "Sports" });
		var count = jdbc.batchUpdate("insert into category values(?,?)", params);
		assertEquals(3, count.length);
	}
}
