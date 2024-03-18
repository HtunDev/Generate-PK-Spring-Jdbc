package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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
	@DisplayName("1. Create Category")
	@Order(1)
	void test1() {
		var c = new Category();
		c.setName("Foods");

		var id = categoryDao.create(c);
		System.out.println(c.getName());
		assertEquals(1, id);
	}

	@Test
	@DisplayName("2. Update Category")
	@Order(2)
	void test2() {
		var c = new Category();
		c.setId(1);
		c.setName("Drinks");

		int count = categoryDao.update(c);
		System.out.println(c.getName());
		assertEquals(1, count);
	}

	@Test
	@DisplayName("3. Find Category By Id")
	@Order(3)
	void test3() {
		Category c = categoryDao.findById(1);
		System.out.println(c);
		assertEquals("Drinks", c.getName());
	}

	@Test
	@DisplayName("4. Find Category By Name Like")
	@Order(4)
	void test4() {
		List<Category> list = categoryDao.findByNameLike("Drin");
		System.out.println(list);
		assertEquals(1, list.size());
	}

	@Test
	@DisplayName("5. Find Count By Name Like")
	@Order(5)
	void test5() {
		int count = categoryDao.findCountByNameLike("Drin");
		System.out.println(count);
		assertEquals(1, count);
	}

	@Test
	@DisplayName("6. Find Count By Name Like")
	@Order(6)
	void test6() {
		int count = categoryDao.delete(1);
		System.out.println(count);
		assertEquals(1, count);
	}
}
