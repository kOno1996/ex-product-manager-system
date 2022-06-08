package com.example.demo.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Account extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	private final User user;

	// GrantedAuthorityメソッド・・・Collection型を返す、ユーザ権限を表すオブジェクト
	// GrantedAuthority自体はインターフェース
	
	public Account(User user, Collection<GrantedAuthority> authorityList) {
        super(user.getName(), user.getPassword(), authorityList);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
	

}
