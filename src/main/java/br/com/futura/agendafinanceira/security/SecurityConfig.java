package br.com.futura.agendafinanceira.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("usuario").password("prisma").roles("USER").and()
			.withUser("admin").password("prisma").roles("ADMIN");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	 	http.authorizeRequests()
	 		.anyRequest().authenticated().and()
	 		.logout().logoutSuccessUrl("/login.xhtml?logout").permitAll().and()
	 		.formLogin().loginPage("/login.xhtml")
	 			.failureUrl("/login.xhtml?erro").permitAll();

	 	
	 	
//		 		.antMatchers("/javax.faces.resource/**").permitAll()
//		 		.antMatchers("/**").permitAll()
//		 		.anyRequest().authenticated()
//		 		.and()
//	 		.logout()
//	 			.logoutSuccessUrl("/login.xhtml?logout")
//	 			.permitAll()
//	 			.and()
//	 		.formLogin()
//	 			.loginPage("/login.xhtml")
//	 			.failureUrl("/login.xhtml?erro")
//	 			.permitAll();
	}	

}
