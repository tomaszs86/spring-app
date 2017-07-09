package app.spring.controller;

public abstract class BaseController {

	protected String getFullViewName(String controllerName, String viewName) {
		return controllerName + "/" + viewName;
	}
}
