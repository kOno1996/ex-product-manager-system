package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.DatabaseUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DatabaseUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//ユーザの認証方式を決定するためのメソッド(ユーザの認証方式には大きく分けてインメモリ(メモリ内でユーザ情報を管理する), DB内でユーザ情報を管理する, LDAPの認証方式がある)
		//例ではインメモリでのユーザ認証方式を扱う
		/*
		 * auth.inMemoryAuthentication().withUser("user@com").password(encoder.encode(
		 * "user")).roles("USER")
		 * .and().withUser("admin@com").password(encoder.encode("admin")).roles("ADMIN")
		 * ;
		 */
		
		//authオブジェクトには認証に使うUserDetailsServiceを設定するためのメソッドが用意されている(userDetailsServiceインスタンスをセットする)
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//セキュリティの対象外を設定
	@Override
	public void configure(WebSecurity web)throws Exception{
		web.ignoring()
		.antMatchers("/css/**", "addInterlocking.js");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		//webアプリケーションが管理しているリソースへのアクセス制御の設定をするためのメソッド		
		//認証方式：httpBasic: 				
		http.authorizeRequests()
		//cssも含めてユーザ認証済みであることが条件となっているのでログイペ―ジが表示されたときにcssが適応されない問題がでる：antMatchersでcssは認証なしでも表示できるよう許可している
		.antMatchers("/user/**").permitAll()
		.antMatchers("/signin").permitAll()
		.antMatchers("/product/**").permitAll()
		.antMatchers("/product-list/**").permitAll()
		//.antMatchers("/buy/mail").permitAll()
		.and().authorizeRequests().antMatchers("/buy/**").hasRole("USER")
		
		//anyRequest, authenticated(全てのリクエストに対して認証済みであることを要求している)=>ログインしていないとこのwebアプリケーション配下のリソースにはアクセスできないという設定をしている
		.anyRequest().authenticated();
		
		http
		.formLogin().loginProcessingUrl("/login").loginPage("/signin")
		//ログインページでユーザは認証されていない状態なのでpermitAllメソッドで全ての認証を許可している
		.usernameParameter("name").passwordParameter("password").permitAll()
		.defaultSuccessUrl("/product-list", true)
		.and().logout().logoutUrl("/logout")
		.invalidateHttpSession(true)
		//クッキーを削除する
		.deleteCookies("JSESSIONID");
		
	}
	
	//Springの依存性注入(あらゆるところから呼び出せるようにする)
	@Bean
	public PasswordEncoder passwordEncoder() {
		//ユーザ認証の際のパスワードをハッシュ化して保存する設定
		//実際の現場だと組み込みのクラスを使うとロジックが分かってしまうため、パスワードエンコーダーはプロジェクト独自のものを利用した方がよい(ストレッチングなど)
		//ストレッチング(ハッシュ化された文字列をさらにハッシュ化する)
		return new BCryptPasswordEncoder();
	}
}
