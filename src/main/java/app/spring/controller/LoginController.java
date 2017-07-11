package app.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.spring.form.LoginForm;
import app.spring.viewmodel.LoginViewModel;

@Controller
public class LoginController extends BaseController {

	private static final String CONTROLLER_NAME = "login";
	private static final String VIEW_MODEL_NAME = "loginForm";

	@Autowired
	LoginViewModel loginViewModel;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		loginViewModel.setLoginForm(new LoginForm());

		model.addAttribute(loginViewModel);
		return getFullViewName(CONTROLLER_NAME, VIEW_MODEL_NAME);
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {

		model.addAttribute("error", "true");
		model.addAttribute(loginViewModel);

		return getFullViewName(CONTROLLER_NAME, VIEW_MODEL_NAME);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {

		return getFullViewName(CONTROLLER_NAME, VIEW_MODEL_NAME);
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {

		return getFullViewName(CONTROLLER_NAME, "403");
	}
	
	
}
