package com.HAH.jdbc.model.dao;

import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import com.HAH.jdbc.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private JdbcOperations jdbc;

	@Value("${dml.category.insert}")
	private String insertSql;

	public int create(Category c) {
		PreparedStatementCreator creator = conn -> {
			var stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c.getName());
			return stmt;
		};

		PreparedStatementCallback<Integer> callback = stmt -> {
			stmt.executeUpdate();
			var rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		};
		return jdbc.execute(creator, callback);
	}
}
