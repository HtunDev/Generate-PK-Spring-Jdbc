package com.HAH.jdbc.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import com.HAH.jdbc.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private JdbcOperations jdbc;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SimpleJdbcInsert insert;

	@Value("${dml.category.insert}")
	private String insertSql;

//	public int create(Category c) {
//
//		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(insertSql, Types.VARCHAR);
//		factory.setReturnGeneratedKeys(true);
//		PreparedStatementCreator creator = factory.newPreparedStatementCreator(List.of(c.getName()));
//
//		var keyholder = new GeneratedKeyHolder();
//		jdbc.update(creator, keyholder);
//		return keyholder.getKey().intValue();
//	}

	public int create(Category c) {

		Map<String, Object> param = new HashMap<>();
		param.put("name", c.getName());

		return insert.executeAndReturnKey(param).intValue();
	}

}
