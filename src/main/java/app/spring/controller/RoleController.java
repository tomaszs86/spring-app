package app.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.spring.model.Role;
import app.spring.repository.RoleRepository;
import app.spring.viewmodel.RoleViewModel;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

	private final String controllerName = "role";
	
	@Autowired
	private RoleViewModel roleViewModel;
	
	@Autowired
	private RoleRepository roleService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model) {
		
		List<Role> roles = new ArrayList<Role>();
		roles = this.roleService.findAll();

		this.roleViewModel.setRoles(roles);
		model.addAttribute("roleViewModel", this.roleViewModel);

		return getFullViewName(this.controllerName, "index");
	}
}
