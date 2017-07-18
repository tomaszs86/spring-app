package app.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "app.spring")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Autowired
		@Qualifier("userDetailsServiceImpl")
    	public UserDetailsService userDetailsService;
		
	 	@Bean
	 	public PasswordEncoder passwordEncoder() {
	 		return new BCryptPasswordEncoder();
	 	}
		
	 	@Bean
	 	public DaoAuthenticationProvider authProvider() {
	 	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 	    authProvider.setUserDetailsService(userDetailsService);
	 	    authProvider.setPasswordEncoder(passwordEncoder());	 	  
	 	    return authProvider;
	 	}

	 	@Autowired
	 	public AccessDeniedHandler accessDeniedHandler;
	 	
	 	@Autowired
	 	public AuthenticationSuccessHandler successHandler;
	 	
	 	@Autowired
	 	public AuthenticationFailureHandler failureHandler;

	 	 //@Autowired
	     //private CustomAuthenticationProvider authProvider;
	 	
	    @Override
		protected void configure(HttpSecurity http) throws Exception {
			
	    	http
            .authorizeRequests()
                .antMatchers("/", "/home", "/user/register").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
	            .loginPage("/login")	            
				.loginProcessingUrl("/login")
				.successHandler(successHandler)
				//.failureUrl("/loginfailed")
				.failureHandler(failureHandler)
				.usernameParameter("username").passwordParameter("password")
				.permitAll()
            .and()
	            .logout()
	            .logoutSuccessUrl("/logout").permitAll();
		    
	    	 // When the user has logged in as XX. But access a page that requires role YY, AccessDeniedException will throw.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	    	
	        
		}	
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	
	    	/*
	    	auth.inMemoryAuthentication().withUser("test").password("test").roles("USER");
	    	auth.userDetailsService(userDetailsService());	    	
	    	auth.authenticationProvider(authProvider);
	    	*/
	    	
	    	auth.authenticationProvider(authProvider());	    	
	    }
}
