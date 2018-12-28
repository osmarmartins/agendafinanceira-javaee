package br.com.futura.agendafinanceira.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
			.antMatchers(HttpMethod.GET, "/setor*.xhtml").hasRole("SETOR")
			.antMatchers("/setor*.xhtml").hasRole("SETOR_MANUTENCAO")
		
			.antMatchers(HttpMethod.GET, "/conta*.xhtml").hasRole("CONTA")
			.antMatchers("/conta*.xhtml").hasRole("CONTA_MANUTENCAO")
			
			.antMatchers(HttpMethod.GET, "/fornecedor*.xhtml").hasRole("FORNECEDOR")
			.antMatchers(HttpMethod.GET, "/contatocadastro.xhtml").hasRole("FORNECEDOR")
			.antMatchers("/fornecedor*.xhtml").hasRole("FORNECEDOR_MANUTENCAO")
			.antMatchers("/contatocadastro.xhtml").hasRole("FORNECEDOR_MANUTENCAO")

			.antMatchers(HttpMethod.GET, "/usuario*.xhtml").hasRole("USUARIO")
			.antMatchers("/usuario*.xhtml").hasRole("USUARIO_MANUTENCAO")

			.antMatchers(HttpMethod.GET, "/usuario*.xhtml").hasRole("USUARIO")
			.antMatchers("/usuario*.xhtml").hasRole("USUARIO_MANUTENCAO")
			
			.antMatchers(HttpMethod.GET, "/pagamento*.xhtml").hasRole("PAGAMENTO")
			.antMatchers("/pagamento*.xhtml").hasRole("PAGAMENTO_MANUTENCAO")

			.antMatchers(HttpMethod.GET, "/baixa*.xhtml").hasRole("BAIXA")
			.antMatchers("/baixa*.xhtml").hasRole("BAIXA_MANUTENCAO")
			
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
 			.permitAll();

	}	

}
