package com.HAH.jdbc.model.dao;

import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.HAH.jdbc.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private JdbcOperations jdbc;

	@Value("${dml.category.insert}")
	private String insertSql;

	public int create(Category c) {

		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(insertSql, Types.VARCHAR);
		factory.setReturnGeneratedKeys(true);
		PreparedStatementCreator creator = factory.newPreparedStatementCreator(List.of(c.getName()));

		var keyholder = new GeneratedKeyHolder();
		jdbc.update(creator, keyholder);
		return keyholder.getKey().intValue();
	}
}
