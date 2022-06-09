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
		product.setShoName(rs.getString("sho_name"));
		product.setChuName(rs.getString("chu_name"));
		product.setDaiName(rs.getString("dai_name"));
		product.setShoId(rs.getInt("sho_id"));
		product.setChuId(rs.getInt("chu_id"));
		product.setDaiId(rs.getInt("dai_id"));
		return product;
	};
	
	public Page<Product> showAll(Pageable pageable){		
		//String sql = "SELECT * FROM items ORDER BY category LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id, i.name, condition, brand, price, shipping, description, ");
		sql.append("ca.id sho_id, ");
		sql.append("ca.name sho_name, ");
		sql.append("ca2.id chu_id, ");
		sql.append("ca2.name chu_name, ");
		sql.append("ca3.id dai_id, ");
		sql.append("ca3.name dai_name ");
		sql.append("FROM items AS i ");
		sql.append("left join category AS ca on i.category = ca.id ");
		sql.append("left join category AS ca2 on ca.parent = ca2.id ");
		sql.append("left join category AS ca3 on ca2.parent = ca3.id ");
		//sql.append("WHERE i.category IS NOT NULL OR ca.parent is NOT NULL OR ca2.parent IS NOT NULL ");
		sql.append("ORDER BY category ");
		sql.append("LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset());
		
		List<Product> productList = template.query(sql.toString(), PRODUCT_ROWMAPPER);
		
		String totalSql = "SELECT COUNT(*) FROM items";
		int total = template.queryForObject(totalSql, new MapSqlParameterSource(), Integer.class);
		
		return new PageImpl<Product>(productList, pageable, total);
	}
	
	public Page<Product> showPage(Pageable pageable, Integer number){
		String sql ="SELECT * FROM items ORDER BY category LIMIT " + pageable.getPageSize() + " OFFSET " + number;
		List<Product> productList = template.query(sql, PRODUCT_ROWMAPPER);
		
		String totalSql = "SELECT COUNT(*) FROM items";
		int total = template.queryForObject(totalSql, new MapSqlParameterSource(), Integer.class);
		return new PageImpl<Product>(productList, pageable, total);
	}
	
	public Page<Product> daiCategoryPage(Pageable pageable, int daiId){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id, i.name, condition, brand, price, shipping, description, ");
		sql.append("ca.id sho_id, ");
		sql.append("ca.name sho_name, ");
		sql.append("ca2.id chu_id, ");
		sql.append("ca2.name chu_name, ");
		sql.append("ca3.id dai_id, ");
		sql.append("ca3.name dai_name ");
		sql.append("FROM items AS i ");
		sql.append("left join category AS ca on i.category = ca.id ");
		sql.append("left join category AS ca2 on ca.parent = ca2.id ");
		sql.append("left join category AS ca3 on ca2.parent = ca3.id ");
		sql.append("WHERE ca3.id=:daiId ");
		sql.append("LIMIT " + pageable.getPageSize() + "OFFSET " + pageable.getOffset());
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("daiId", daiId);
		List<Product> productList = template.query(sql.toString(), param, PRODUCT_ROWMAPPER);
		
		String totalSql = "SELECT COUNT(*) FROM items AS i LEFT JOIN category AS ca on i.category = ca.id LEFT JOIN category AS ca2 ON ca.parent = ca2.id ";
		totalSql = totalSql + "LEFT JOIN category AS ca3 ON ca2.parent = ca3.id WHERE ca3.id=:daiId";
		
		int total = template.queryForObject(totalSql, new MapSqlParameterSource().addValue("daiId", daiId), Integer.class);
		return new PageImpl<Product>(productList, pageable, total);
	}
}
