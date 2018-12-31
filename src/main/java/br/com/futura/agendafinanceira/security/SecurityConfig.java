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
			.withUser("usuario").password("prisma").roles("SETOR", "CONTA", "FORNECEDOR").and()
			.withUser("admin").password("prisma").roles("PAGAMENTO", "BAIXA");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		
//		.headers()
//			.frameOptions()
//			.sameOrigin()
//			.and()
		
		.exceptionHandling()
			.accessDeniedPage("/erro-403.xhtml")
			.and()
			
		.authorizeRequests()
			.antMatchers("/setor/**").hasRole("SETOR")
			.antMatchers("/conta/**").hasRole("CONTA")
			
//			.antMatchers(HttpMethod.GET, "/fornecedor*.xhtml").hasRole("FORNECEDOR")
//			.antMatchers(HttpMethod.GET, "/contatocadastro.xhtml").hasRole("FORNECEDOR")
//			.antMatchers("/fornecedor*.xhtml").hasRole("FORNECEDOR_MANUTENCAO")
//			.antMatchers("/contatocadastro.xhtml").hasRole("FORNECEDOR_MANUTENCAO")
//
//			.antMatchers(HttpMethod.GET, "/usuario*.xhtml").hasRole("USUARIO")
//			.antMatchers("/usuario*.xhtml").hasRole("USUARIO_MANUTENCAO")
//
//			.antMatchers(HttpMethod.GET, "/usuario*.xhtml").hasRole("USUARIO")
//			.antMatchers("/usuario*.xhtml").hasRole("USUARIO_MANUTENCAO")
//			
//			.antMatchers(HttpMethod.GET, "/pagamento*.xhtml").hasRole("PAGAMENTO")
//			.antMatchers("/pagamento*.xhtml").hasRole("PAGAMENTO_MANUTENCAO")
//
//			.antMatchers(HttpMethod.GET, "/baixa*.xhtml").hasRole("BAIXA")
//			.antMatchers("/baixa*.xhtml").hasRole("BAIXA_MANUTENCAO")
			
			.anyRequest().authenticated()
			.antMatchers("/**").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/javax.faces.resource/**").permitAll()
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
