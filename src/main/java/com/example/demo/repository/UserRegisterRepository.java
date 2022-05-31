package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;


@Repository
public class UserRegisterRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	public static final RowMapper<User> USER_ROWMAPPER = (rs, i) ->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setAuthority(rs.getInt("authority"));
		return user;
	};
	
	public void userInsert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, password) VALUES(:name, :password)";
		template.update(sql, param);
	}
	
	public User login(String email, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user WHERE email =:email AND password=:password");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<User> user =  template.query(sql.toString(), param, USER_ROWMAPPER);
		
		return user.get(0);
	}
}
