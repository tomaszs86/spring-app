package app.spring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import app.spring.form.RoleForm;
import app.spring.model.Role;
import app.spring.repository.RoleRepository;
import app.spring.security.service.SecurityService;
import app.spring.viewmodel.RoleViewModel;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static final String CONTROLLER_NAME = "role";
	private static final String VIEW_MODEL_NAME = "roleViewModel";
	
	@Autowired
	private RoleViewModel roleViewModel;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Qualifier("securityService")
	private SecurityService securityService;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String index(Model model) {
		
		List<Role> roles = roleRepository.findAll();
		
		roleViewModel.setRoles(roles);
		model.addAttribute(VIEW_MODEL_NAME, roleViewModel);
		model.addAttribute("user", getPrincipal());
		return getFullViewName(CONTROLLER_NAME, "index");
	}
		
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details() {

		RedirectView redirectView = getRedirectView(CONTROLLER_NAME,"list");				
		return new ModelAndView(redirectView);
	}
	
	//@PreAuthorize("hasRole('ADMIN_TEST_LIST')")
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String details(HttpServletRequest request,@PathVariable("id") Long id, Model model) {

		Role role = roleRepository.findOne(id);

		if (role == null) {
			throw new ResourceNotFoundException();
		}

		roleViewModel.setRole(role);
		model.addAttribute(VIEW_MODEL_NAME, roleViewModel);
		return getFullViewName(CONTROLLER_NAME, "details");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {

		roleViewModel.setRoleForm(new RoleForm());
		model.addAttribute(roleViewModel);
		return getFullViewName(CONTROLLER_NAME, "create");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView submitCreate(@Valid @ModelAttribute(value="roleViewModel") RoleViewModel roleViewModel, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return new ModelAndView(getFullViewName(CONTROLLER_NAME, "create"));
		}

		Role role = new Role();
		role.setName(roleViewModel.getRoleForm().getName());		
		roleRepository.save(role);

		RedirectView redirectView = this.getRedirectView(CONTROLLER_NAME,"list");	
		return new ModelAndView(redirectView);
	}
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("@securityService.checkRight(authentication, #id)")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(HttpServletRequest req, @PathVariable("id") Long id, Model model, Principal principal, Authentication authentication, HttpServletRequest request) {

		/*
		System.out.println(principal.getName());
		System.out.println(authentication.getName());
		Principal requestPrincipal = request.getUserPrincipal();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		*/
		
		Role role = roleRepository.findOne(id);
		
		if (role == null) {
			throw new ResourceNotFoundException();
		}
		
		RoleForm roleForm = new RoleForm();
		roleForm.setName(role.getName());
		roleForm.setIdRole(role.getIdRole());
		
		roleViewModel.setRoleForm(roleForm);
		model.addAttribute(VIEW_MODEL_NAME, roleViewModel);
		return getFullViewName(CONTROLLER_NAME, "edit");
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView postEditForm(@ModelAttribute @Valid RoleViewModel roleViewModel,BindingResult result, Model model) {

		
		if (result.hasErrors()) {
			model.addAttribute(VIEW_MODEL_NAME, roleViewModel);
			return new ModelAndView(getFullViewName(VIEW_MODEL_NAME, "edit"));
		}

		RoleForm roleForm = roleViewModel.getRoleForm();
		Role role = new Role();
		role.setIdRole(roleForm.getIdRole());
		role.setName(roleForm.getName());	
		roleRepository.save(role);

		RedirectView redirectView = this.getRedirectView(CONTROLLER_NAME,"");	
		return new ModelAndView(redirectView);
	}
	
	
}
