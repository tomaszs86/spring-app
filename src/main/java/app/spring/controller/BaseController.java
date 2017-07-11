package app.spring.controller;

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
}
