package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.domain.Account;
import com.example.demo.repository.UserRegisterRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRegisterRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.login(name);
		if (user == null) {
			throw new UsernameNotFoundException("入力されたメールアドレスは登録されていません");
		}
		
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
        
        if (user.getAuthority() == 1) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else if(user.getAuthority() == 2) {
        	authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new Account(user, authorityList);

		
		// 認可権限の付与
		//Collection<GrantedAuthority> authorities = new ArrayList<>();
		/*
		 * if (Integer.parseInt(user.getAuthority()) == 1) { authorities.add(new
		 * SimpleGrantedAuthority("ROLE_ADMIN")); } else if
		 * (Integer.parseInt(user.getAuthority()) == 2) { authorities.add(new
		 * SimpleGrantedAuthority("ROLE_USER")); }
		 */
	}

}
