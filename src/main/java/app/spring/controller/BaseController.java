package app.spring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.view.RedirectView;

public abstract class BaseController {

	protected String getFullViewName(String controllerName, String viewName) {
		return controllerName + "/" + viewName;
	}
	
	protected RedirectView getRedirectView(String controllerName, String viewName) {
		
		RedirectView redirectView = new RedirectView("/" + getFullViewName(controllerName, viewName));
		redirectView.setContextRelative(true);
		redirectView.setExposeModelAttributes(false);
		
		return redirectView;
	}
	
	protected String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
