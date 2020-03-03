package br.com.futura.agendafinanceira.security;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {SecurityWebApplicationInitializer.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Inject
	private AutenticadorUsuario autenticadorUsuario;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(autenticadorUsuario);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		
		.headers()
			.frameOptions()
			.sameOrigin()
			.and()
		
		.exceptionHandling()
			.accessDeniedPage("/erro-403.xhtml")
			.and()
			
		.authorizeRequests()
			.antMatchers("/setor/**").hasRole("SETOR")
			.antMatchers("/conta/**").hasRole("CONTA")
			.antMatchers("/fornecedor/**").hasRole("FORNECEDOR")
			.antMatchers("/usuario/**").hasRole("USUARIO")
			.antMatchers("/pagamento/**").hasRole("PAGAMENTO")
			.antMatchers("/baixa/**").hasRole("BAIXA_PAGAMENTO")
			.antMatchers("/relatorios/**").hasRole("RELATORIOS")
			
			.antMatchers("/javax.faces.resource/**").permitAll()
			.anyRequest().authenticated()
	 		.and()		
	 		
 		.logout()
 			.deleteCookies("JSESSIONID")
 			.logoutSuccessUrl("/login.xhtml?logout")
 			.permitAll()
 			.and()
 			
 		.formLogin()
 			.loginPage("/login.xhtml")
 			.failureUrl("/login.xhtml?erro")
 			.defaultSuccessUrl("/index.xhtml", true)
 			.permitAll();

	}	

}
