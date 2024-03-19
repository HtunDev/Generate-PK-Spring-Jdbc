package com.HAH.jdbc.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
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
	@Sql(statements = { "insert into category (name) values ('Foods')",
			"insert into category (name) values ('Drinks')" })
	void test1() {
		var category = categoryDao.findById(1);
		var product = new Product();
		product.setCategory(category);
		product.setName("Sp Bakery");
		product.setPrice(2300);

		var id = productDao.create(product);
		assertEquals(1, id);
	}

	@Test
	@Order(2)
	@DisplayName("2. Find Products By Id")
	void test2() {
		var p = productDao.findProductsById(1);
		assertNotNull(p);
		assertEquals("Sp Bakery", p.getName());
		assertEquals(2300, p.getPrice());
		assertEquals("Foods", p.getCategory().getName());
		
		assertNull(productDao.findProductsById(2));
	}

	@Test
	@Order(3)
	@DisplayName("3. Find Products By Category")
	void test3() {
		List<Product> list = productDao.findProductsByCategory(1);
		assertEquals(1, list.size());
		assertTrue(productDao.findProductsByCategory(2).isEmpty());
	}

	@Test 
	@Order(4)
	@DisplayName("4. Search")
	void test4() {
		List<Product> list = productDao.search("Foods");
		assertEquals(1, list.size());
		assertEquals(1, productDao.search("Sp Bakery").size());
		assertTrue(productDao.findProductsByCategory(2).isEmpty());
	}

	@Test
	@Order(5)
	@DisplayName("5. Update Product")
	void test5() {
		var p = productDao.findProductsById(1);
		p.setName("Sp Bakery Bread");
		p.setPrice(4000);

		int count = productDao.update(p);

		var other = productDao.findProductsById(1);

		assertEquals(1, count);
		assertEquals(p.getName(), other.getName());
		assertEquals(p.getPrice(), other.getPrice());
	}

	@Test
	@Order(6)
	@DisplayName("6. Delete Products")
	void test6() {
		int count = productDao.delete(1);
		assertEquals(1, count);
		assertNull(productDao.findProductsById(1));
	}

}
