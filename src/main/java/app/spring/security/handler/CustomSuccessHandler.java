package app.spring.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	 @Override
	    protected void handle(HttpServletRequest request, 
	      HttpServletResponse response, Authentication authentication) throws IOException {
	        String targetUrl = determineTargetUrl(authentication);
	  
	        if (response.isCommitted()) {	            
	            return;
	        }
	        
	        /*
	        HttpSession session = httpServletRequest.getSession();
	        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        session.setAttribute("username", authUser.getUsername());
	        session.setAttribute("authorities", authentication.getAuthorities());
	 
	        //set our response to OK status
	        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	         */
	  
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }
	 
	 protected String determineTargetUrl(Authentication authentication) {
	        String url="";
	         
	        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
	         
	        List<String> roles = new ArrayList<>();
	 
	        for (GrantedAuthority a : authorities) {
	            roles.add(a.getAuthority());
	        }
	 
	        /*
	        if (isDba(roles)) {
	            url = "/db";
	        } else if (isAdmin(roles)) {
	            url = "/admin";
	        } else if (isUser(roles)) {
	            url = "/home";
	        } else {
	            url="/accessDenied";
	        }
	        */
	        
	        url = "/role/list";
	 
	        return url;
	    }
	  
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	     
	    private boolean isUser(List<String> roles) {
	        if (roles.contains("ROLE_USER")) {
	            return true;
	        }
	        return false;
	    }
	 
	 
	
}
