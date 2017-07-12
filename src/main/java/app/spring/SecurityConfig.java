package app.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import app.spring.security.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "app.spring")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Autowired
		@Qualifier("userDetailsService")
	    private UserDetailsService userDetailsService;
		
	 	@Bean
	    public AccessDeniedHandler accessDeniedHandler(){
	        return new CustomAccessDeniedHandler();
	    }
	 	
	    @Override
		protected void configure(HttpSecurity http) throws Exception {
			
	    	http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
	            .loginPage("/login")	            
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/role/list")
				.failureUrl("/loginfailed")
				.usernameParameter("username").passwordParameter("password")
				.permitAll()
            .and()
	            .logout()
	            .logoutSuccessUrl("/logout").permitAll();
		    
	    	 // When the user has logged in as XX. But access a page that requires role YY, AccessDeniedException will throw.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	    	
			
		}	  
}
