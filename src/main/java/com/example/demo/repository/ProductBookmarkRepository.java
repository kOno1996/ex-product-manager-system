package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public class ProductBookmarkRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, i)->{
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		return product;
	};
	
	
	public void productRegister(Product product) {
		String totalSql = "SELECT COUNT(*) FROM bookmark";
		int total = template.queryForObject(totalSql, new MapSqlParameterSource(), Integer.class);
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(product);
		String sql = "INSERT INTO bookmark(id, name, price, items_id) VALUES(" + (total + 1) + ", :name, :price, :id)";
		template.update(sql, param);
	}
	
	public Product getProduct(int id) {
		String sql = "SELECT id, name, price FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, PRODUCT_ROW_MAPPER);
	}
	
	public List<Product> getBookmarkList(){
		String sql = "SELECT items_id AS id, name, price FROM bookmark";
		
		return template.query(sql, PRODUCT_ROW_MAPPER);
	}
}
