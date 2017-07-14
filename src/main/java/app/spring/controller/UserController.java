package app.spring.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import app.spring.form.UserForm;
import app.spring.model.Language;
import app.spring.model.Role;
import app.spring.model.User;
import app.spring.repository.LanguageRepository;
import app.spring.repository.RoleRepository;
import app.spring.repository.UserRepository;
import app.spring.security.SecurityService;
import app.spring.validator.UserValidator;
import app.spring.viewmodel.UserViewModel;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final String CONTROLLER_NAME = "user";
	private static final String VIEW_MODEL_NAME = "userViewModel";

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
	private LanguageRepository languageRepository;
    
    //@Autowired
    //private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserViewModel userViewModel;
	
	@Autowired
    private UserValidator userValidator;
	
	//@Autowired
    //private SecurityService securityService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {		
		userViewModel.setUserForm(new UserForm());
		model.addAttribute(VIEW_MODEL_NAME, userViewModel);
		return getFullViewName(CONTROLLER_NAME, "register");
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView submitRegister(@ModelAttribute @Valid UserViewModel userViewModel, BindingResult result, Model model) {

		userValidator.validate(userViewModel.getUserForm(), result);
		
		if (result.hasErrors()) {
			return new ModelAndView(getFullViewName(CONTROLLER_NAME, "register"));
		}
		
		Language language = languageRepository.findOne(new Long(1));

		User user = new User();
		user.setDeleted(false);
		user.setEmail("admin@admin.pl");
		user.setEnabled(true);
		user.setLoginAttempts(0);
		user.setName("admin");
		//user.setPassword(passwordEncoder.encode(userViewModel.getUserForm().getPassword()));
		user.setSurname("admin");
		user.setUsername(userViewModel.getUserForm().getUsername());
		user.setLanguage(language);

		Role newRole = new Role();
		newRole.setName("ROLE_USER");
		Set<Role> newRoles = new HashSet<>();
		newRoles.add(newRole);

		user.setRoles(newRoles);
		
		userRepository.save(user);

		//securityService.autologin(userViewModel.getUserForm().getUsername(), userViewModel.getUserForm().getPassword());

		RedirectView redirectView = this.getRedirectView("role","list");	
		return new ModelAndView(redirectView);
	}
}
