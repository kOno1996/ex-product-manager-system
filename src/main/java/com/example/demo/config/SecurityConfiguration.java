package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.DatabaseUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DatabaseUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		
		//ユーザの認証方式を決定するためのメソッド(ユーザの認証方式には大きく分けてインメモリ(メモリ内でユーザ情報を管理する), DB内でユーザ情報を管理する, LDAPの認証方式がある)
		//例ではインメモリでのユーザ認証方式を扱う
		//auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
		
		//authオブジェクトには認証に使うUserDetailsServiceを設定するためのメソッドが用意されている(userDetailsServiceインスタンスをセットする)
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		//webアプリケーションが管理しているリソースへのアクセス制御の設定をするためのメソッド		
		//認証方式：httpBasic: 				
		http.authorizeRequests()
		//cssも含めてユーザ認証済みであることが条件となっているのでログイペ―ジが表示されたときにcssが適応されない問題がでる：antMatchersでcssは認証なしでも表示できるよう許可している
		.antMatchers("/css/**").permitAll()
		.antMatchers("/user/toRegister").permitAll()
		//anyRequest, authenticated(全てのリクエストに対して認証済みであることを要求している)=>ログインしていないとこのwebアプリケーション配下のリソースにはアクセスできないという設定をしている
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		//ログインページでユーザは認証されていない状態なのでpermitAllメソッドで全ての認証を許可している
		.usernameParameter("email").passwordParameter("password").permitAll()
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
