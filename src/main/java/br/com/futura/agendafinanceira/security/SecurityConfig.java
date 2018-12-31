package br.com.futura.agendafinanceira.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("usuario").password("prisma").roles("PAGAMENTO", "RELATORIO").and()
			.withUser("admin").password("prisma").roles("SETOR", "CONTA", "FORNECEDOR", "USUARIO", "PAGAMENTO", "BAIXA");
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
			.antMatchers("/baixa/**").hasRole("BAIXA")
			.antMatchers("/relatorio/**").hasRole("RELATORIO")
			
			.antMatchers("/javax.faces.resource/**").permitAll()
			.anyRequest().authenticated()
	 		.and()		
	 		
 		.logout()
 			.logoutSuccessUrl("/login.xhtml?logout")
 			.permitAll()
 			.and()
 			
 		.formLogin()
 			.loginPage("/login.xhtml")
 			.failureUrl("/login.xhtml?erro")
 			.defaultSuccessUrl("/", true)
 			.permitAll();

	}	

}
