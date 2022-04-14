package com.example.demo.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public class ProductListRepository {
	
	public static final RowMapper<Product> PRODUCT_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		return product;
	};
}
