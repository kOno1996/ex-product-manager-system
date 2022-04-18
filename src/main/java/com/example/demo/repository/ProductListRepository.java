package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public class ProductListRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Product> PRODUCT_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setCondition(rs.getInt("condition"));
		product.setBrand(rs.getString("brand"));
		product.setPrice(rs.getInt("price"));
		product.setShipping(rs.getInt("shipping"));
		product.setDescription(rs.getString("description"));
		return product;
	};
	
	public Page<Product> showAll(){
		String sql = "SELECT * FROM items ORDER BY category";
		return template.query(sql, PRODUCT_ROWMAPPER);
	}
}
