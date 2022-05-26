package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public class ProductDetailRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private static final RowMapper<Product> PRODUCT_ROWMAPPR = (rs, i)->{
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		product.setShoName(rs.getString("sho_name"));
		product.setChuName(rs.getString("chu_name"));
		product.setDaiName(rs.getString("dai_name"));
		//product.setCategoryName(rs.getString("category"));
		product.setBrand(rs.getString("brand"));
		product.setCondition(rs.getInt("condition"));
		product.setDescription(rs.getString("description"));
		return product;
	};
	
	public Product detail(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id id, i.name name, i.price price, ca.name sho_name, ca2.name chu_name, ca3.name dai_name, i.brand brand, i.condition condition, i.description description ");
		sql.append("FROM items as i ");
		sql.append("LEFT JOIN category as ca ON i.category = ca.id ");
		sql.append("LEFT JOIN category as ca2 ON ca.parent = ca2.id ");
		sql.append("LEFT JOIN category as ca3 ON ca2.parent = ca3.id ");
		sql.append("WHERE i.id=:id");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql.toString(), param, PRODUCT_ROWMAPPR);
	}
}
