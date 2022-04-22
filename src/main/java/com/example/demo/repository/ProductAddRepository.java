package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public class ProductAddRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Product> DAI_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		/*
		 * product.setId(rs.getInt("id")); product.setName(rs.getString("name"));
		 * product.setCondition(rs.getInt("condition"));
		 * product.setBrand(rs.getString("brand"));
		 * product.setPrice(rs.getInt("price"));
		 * product.setShipping(rs.getInt("shipping"));
		 * product.setDescription(rs.getString("description"));
		 * product.setShoId(rs.getInt("sho_id"));
		 */
		//product.setShoName(rs.getString("sho_name"));
		//product.setChuId(rs.getInt("chu_id"));
		//product.setChuName(rs.getString("chu_name"));
		//product.setDaiId(rs.getInt("dai_id"));
		product.setDaiName(rs.getString("dai_name"));
		return product;
	};
	
	public static final RowMapper<Product> CHU_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setChuName(rs.getString("chu_name"));
		return product;
	};
	
	public static final RowMapper<Product> SHO_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setShoName(rs.getString("sho_name"));
		return product;
	};
	
	public static final RowMapper<Product> PRODUCT_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		return product;
	};
	
	public List<Product> daiName(){
		StringBuilder sql = new StringBuilder();
		//sql.append("SELECT ca.name sho_name, ca2.name chu_name, ca3.name dai_name ");
		sql.append("SELECT DISTINCT ca3.name dai_name ");
		sql.append("FROM items AS i ");
		sql.append("left join category AS ca ON i.category = ca.id ");
		sql.append("left join category AS ca2 ON ca.parent = ca2.id ");
		sql.append("left join category AS ca3 ON ca2.parent = ca3.id ");
		sql.append("WHERE ca2.parent is not NULL");
		return template.query(sql.toString(), DAI_ROWMAPPER);
	}
	
	public List<Product> chuName(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ca2.name chu_name ");
		sql.append("FROM items as i ");
		sql.append("left join category as ca on i.category = ca.id ");
		sql.append("left join category as ca2 on ca.parent = ca2.id ");
		sql.append("WHERE ca.parent IS NOT NULL");
		return template.query(sql.toString(), CHU_ROWMAPPER);
	}
	
	public List<Product> shoName(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ca.name sho_name ");
		sql.append("FROM items as i ");
		sql.append("left join category as ca on i.category = ca.id ");
		sql.append("WHERE i.category IS NOT NULL");
		return template.query(sql.toString(), SHO_ROWMAPPER);
	}
	
	public void add(Product product) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(product);
	}
}
