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
public class ProductEditRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Product> PRODUCT_DAI_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setDaiId(rs.getInt("dai_id"));
		product.setDaiName(rs.getString("dai_name"));
		product.setChuId(rs.getInt("chu_parent"));
		return product;
	};
	
	private static final RowMapper<Product> PRODUCT_CHU_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		//product.setDaiId(rs.getInt("dai_id"));
		//product.setDaiName(rs.getString("dai_name"));
		product.setChuId(rs.getInt("chu_id"));
		product.setChuName(rs.getString("chu_name"));
		product.setChuParent(rs.getInt("chu_parent"));
		product.setShoId(rs.getInt("sho_parent"));
		return product;
	};
	
	private static final RowMapper<Product> PRODUCT_SHO_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setDaiId(rs.getInt("dai_id"));
		//product.setDaiName(rs.getString("dai_name"));
		product.setChuId(rs.getInt("chu_id"));
		//product.setChuName(rs.getString("chu_name"));
		product.setShoId(rs.getInt("sho_id"));
		product.setShoName(rs.getString("sho_name"));
		return product;
	};
	
	private static final RowMapper<Product> PRODUCT_ROWMAPPER = (rs, i)->{
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		product.setShoId(rs.getInt("sho_id"));
		product.setShoName(rs.getString("sho_name"));
		product.setChuId(rs.getInt("chu_id"));
		product.setChuName(rs.getString("chu_name"));
		product.setDaiId(rs.getInt("dai_id"));
		product.setDaiName(rs.getString("dai_name"));
		product.setBrand(rs.getString("brand"));
		product.setCondition(rs.getInt("condition"));
		product.setDescription(rs.getString("description"));
		return product;
	};
	
	public List<Product> editDaiCategory() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ca2.parent chu_parent, ca3.name dai_name, ca3.id dai_id ");
		sql.append("FROM items AS i ");
		sql.append("LEFT JOIN category as ca ON i.category = ca.id ");
		sql.append("LEFT JOIN category as ca2 ON ca.parent = ca2.id ");
		sql.append("LEFT JOIN category as ca3 ON ca2.parent = ca3.id ");
		sql.append("WHERE i.category IS NOT NULL OR ca.parent IS NOT NULL OR ca2.parent IS NOT NULL");
		return template.query(sql.toString(), PRODUCT_DAI_ROWMAPPER);
	}
	
	public List<Product> editChuCategory() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ca.parent sho_parent, ca2.name chu_name, ca2.id chu_id, ca2.parent chu_parent ");
		sql.append("FROM items AS i ");
		sql.append("LEFT JOIN category as ca ON i.category = ca.id ");
		sql.append("LEFT JOIN category as ca2 ON ca.parent = ca2.id ");
		sql.append("WHERE i.category IS NOT NULL OR ca.parent IS NOT NULL");
		return template.query(sql.toString(), PRODUCT_CHU_ROWMAPPER);
	}
	
	public List<Product> editShoCategory() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ca.name sho_name, ca.id sho_id, ca2.id chu_id, ca3.id dai_id ");
		sql.append("FROM items AS i ");
		sql.append("LEFT JOIN category as ca ON i.category = ca.id ");
		sql.append("LEFT JOIN category as ca2 ON ca.parent = ca2.id ");
		sql.append("LEFT JOIN category as ca3 ON ca2.parent = ca3.id ");
		sql.append("WHERE i.category IS NOT NULL");
		return template.query(sql.toString(), PRODUCT_SHO_ROWMAPPER);
	}
	
	public Product editProduct(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.id id, i.name name, i.price price, ca.id sho_id, ca.name sho_name, ca2.id chu_id, ca2.name chu_name, ca3.id dai_id, ca3.name dai_name, i.brand brand, i.condition condition, i.description description ");
		sql.append("FROM items AS i ");
		sql.append("LEFT JOIN category AS ca ON i.category = ca.id ");
		sql.append("LEFT JOIN category AS ca2 ON ca.parent = ca2.id ");
		sql.append("LEFT JOIN category AS ca3 ON ca2.parent = ca3.id ");
		sql.append("WHERE i.id =:id");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql.toString(), param, PRODUCT_ROWMAPPER);
	}
	
	public void editUpdate(int id, Product product) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(product);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE items SET name=:name, condition=:condition, category=:category, brand=:brand, price=:price, shipping=:shipping, description=:description ");
		sql.append("WHERE id=");
		sql.append(id);
		template.update(sql.toString(), param);
	}
}
