package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.HAH.jdbc.model.configuration.AppConfig;
import com.HAH.jdbc.model.dao.CategoryDao;
import com.HAH.jdbc.model.dto.Category;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class JdbcTest {

	@Autowired
	private CategoryDao categoryDao;

	@Test
	void TestDemo() {
		var c = new Category();
		c.setName("Foods");

		var id = categoryDao.create(c);
		assertEquals(1, id);
	}
}
