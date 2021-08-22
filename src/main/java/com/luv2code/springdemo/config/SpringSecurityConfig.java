package com.luv2code.springdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


		/** Using JDBC authentication instead of hard coding the values*/
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		/**------------------------------------ */
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication()
//			.withUser(users.username("Vikram").password("123").roles("EMPLOYEE","ADMIN"))
//			.withUser(users.username("Manoj").password("456").roles("MANAGER"))
//			.withUser(users.username("Dileep").password("789").roles("ADMIN","EMPLOYEE"))
//			.withUser(users.username("Gowtham").password("012").roles("EMPLOYEE"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/customer/list/**").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
			.antMatchers("/customer/showFormForAdd/**").hasAnyRole("MANAGER","ADMIN")
			.antMatchers("/system/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
}











