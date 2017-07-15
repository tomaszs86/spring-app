package app.spring.security.provider;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	  @Override
	    public Authentication authenticate(Authentication authentication) {
	  
	        String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        /*
	        if (shouldAuthenticateAgainstThirdPartySystem()) {	        
	        use the credentials
	        and authenticate against the third-party system	        
	        } else {
	            return null;
	        }
	        */
	        
	        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
	    }

	 
	    @Override
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(
	          UsernamePasswordAuthenticationToken.class);
	    }
	
}
