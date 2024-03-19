package com.HAH.jdbc.model.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import com.HAH.jdbc.model.dto.Product;

@Repository
public class ProductDao {

	@Autowired
	private NamedParameterJdbcOperations jdbc;

	@Value("${dao.product.create}")
	private String create;
	@Value("${dao.product.update}")
	private String update;
	@Value("${dao.product.delete}")
	private String delete;
	@Value("${dao.product.findProductsById}")
	private String findProductsById;
	@Value("${dao.product.findProductsByCategoryId}")
	private String findProductsByCategoryId;
	@Value("${dao.product.search}")
	private String search;

	private RowMapper<Product> rowMapper;

	public ProductDao() {
		rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
	}

	public int create(Product p) {
		var key = new GeneratedKeyHolder();

		var params = new MapSqlParameterSource();
		params.addValue("name", p.getName());
		params.addValue("categoryId", p.getCategory().getId());
		params.addValue("price", p.getPrice());

		jdbc.update(create, params, key);
		return key.getKey().intValue();
	}

	public Product findProductsById(int id) {
		var params = new HashMap<String, Object>();
		params.put("Id", id);
		return jdbc.queryForStream(findProductsById, params, rowMapper).findFirst().orElseGet(() -> null);
	}

	public List<Product> findProductsByCategory(int CategoryId) {
		var params = new HashMap<String, Object>();
		params.put("categoryId", CategoryId);
		return jdbc.query(findProductsByCategoryId, params, rowMapper);
	}

	public List<Product> search(String keyword) {
		var params = new HashMap<String, Object>();
		params.put("keyword", keyword.toLowerCase().concat("%"));
		return jdbc.query(search, params, rowMapper);
	}

	public int update(Product p) {
		var params = new MapSqlParameterSource();
		params.addValue("Id", p.getId());
		params.addValue("name", p.getName());
		params.addValue("price", p.getPrice());
		return jdbc.update(update, params);
	}

	public int delete(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("Id", id);
		return jdbc.update(delete, params);
	}

}
