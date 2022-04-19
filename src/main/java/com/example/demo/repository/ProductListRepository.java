package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	public Page<Product> showAll(Pageable pageable){
		String sql = "SELECT * FROM items ORDER BY category LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
		List<Product> productList = template.query(sql, PRODUCT_ROWMAPPER);
		
		String totalSql = "SELECT COUNT(*) FROM items";
		int total = template.queryForObject(totalSql, new MapSqlParameterSource(), Integer.class);
		
		return new PageImpl<Product>(productList, pageable, total);
	}
	
	public Page<Product> showPage(Pageable pageable, Integer number){
		number = number - 1;
		String sql ="SELECT * FROM items ORDER BY category LIMIT " + pageable.getPageSize() + " OFFSET " + number;
		List<Product> productList = template.query(sql, PRODUCT_ROWMAPPER);
		
		String totalSql = "SELECT COUNT(*) FROM items";
		int total = template.queryForObject(totalSql, new MapSqlParameterSource(), Integer.class);
		return new PageImpl<Product>(productList, pageable, total);
	}
}
