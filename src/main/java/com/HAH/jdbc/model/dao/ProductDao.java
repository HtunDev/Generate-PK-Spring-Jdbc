package com.HAH.jdbc.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	public int create(Product p) {
		var key = new GeneratedKeyHolder();
		var params = new MapSqlParameterSource();
		params.addValue("name", p.getName());
		params.addValue("categoryId", p.getCategory().getId());
		params.addValue("price", p.getPrice());

		jdbc.update(create, null, key);
		return key.getKey().intValue();
	}

}
