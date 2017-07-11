package app.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 	@Bean
	    public AccessDeniedHandler accessDeniedHandler(){
	        return new CustomAccessDeniedHandler();
	    }
	
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        
	    	auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	    	auth.inMemoryAuthentication().withUser("userB").password("password").roles("ADMIN");
	    	
	    	//.authorities("ROLES_LIST", "USERS_LIST") = error
	    }
	    
	    @Override
		protected void configure(HttpSecurity http) throws Exception {
			
	    	http.authorizeRequests().antMatchers("/", "/home").permitAll();
	    	
	    	// hasAnyRole()
	    	http.authorizeRequests().antMatchers("/role/list", "/role/list/").access("hasRole('USER')");
	    	http.authorizeRequests().antMatchers("/user/list", "/user/list/").access("hasRole('ADMIN')");
	    	
	    	 // When the user has logged in as XX. But access a page that requires role YY, AccessDeniedException will throw.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	    	
	    	http.authorizeRequests().and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/role/list")
					.failureUrl("/loginfailed")
					.usernameParameter("username").passwordParameter("password")					
				.and()
					.logout().logoutSuccessUrl("/logout");
				
			
		}	  
}
