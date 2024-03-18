package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.HAH.jdbc.model.dao.CategoryDao;
import com.HAH.jdbc.model.dao.ProductDao;
import com.HAH.jdbc.model.dto.Product;

@SpringJUnitConfig(locations = "classpath:application.xml")
@TestMethodOrder(OrderAnnotation.class)
public class ProductDaoTest {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Test
	@Order(1)
	@DisplayName("1. Create Product")
	@Sql(statements = {
			"insert into category (name) values ('Foods')",
			"insert into category (name) values ('Drinks')"
	})
	void test1() {
		var category = categoryDao.findById(1);
		var product = new Product();
		product.setCategory(category);
		product.setName("Sp Bakery");
		product.setPrice(2300);
		
		var id = productDao.create(product);
		assertEquals(1, id);
	}
}
