package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.auth.User;

@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROWMAPPER = (rs, i)->{
		User user = new User();
		user.setName(rs.getString("name"));
		return user;
	};
}
