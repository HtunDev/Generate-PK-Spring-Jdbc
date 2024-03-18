package com.HAH.jdbc.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import com.HAH.jdbc.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private SimpleJdbcInsert insert;

	@Value("${dao.category.insert}")
	private String insertSql;
	@Value("${dao.category.update}")
	private String update;
	@Value("${dao.category.findById}")
	private String findById;
	@Value("${dao.category.findByNameLike}")
	private String findByNameLike;
	@Value("${dao.category.findCountByNameLike}")
	private String findCountByNameLike;
	@Value("${dao.category.delete}")
	private String delete;

	private BeanPropertyRowMapper<Category> rowMapper;

	public CategoryDao() {
		rowMapper = new BeanPropertyRowMapper<>(Category.class);
	}

	public int create(Category c) {

		Map<String, Object> param = new HashMap<>();
		param.put("name", c.getName());

		return insert.executeAndReturnKey(param).intValue();
	}

	public int update(Category c) {
		return insert.getJdbcTemplate().update(update, c.getName(), c.getId());
	}

	public Category findById(int id) {
		return insert.getJdbcTemplate().queryForObject(findById, rowMapper, id);
	}

	public List<Category> findByNameLike(String name) {
		return insert.getJdbcTemplate().query(findByNameLike, rowMapper, name.toLowerCase().concat("%"));
	}

	public int findCountByNameLike(String name) {
		return insert.getJdbcTemplate().queryForObject(findCountByNameLike, Integer.class,
				name.toLowerCase().concat("%"));
	}

	public int delete(int id) {
		return insert.getJdbcTemplate().update(delete, id);
	}

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

}
