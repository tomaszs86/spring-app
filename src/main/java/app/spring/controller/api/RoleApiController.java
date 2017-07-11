package app.spring.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spring.model.Role;
import app.spring.repository.RoleRepository;


@RestController
public class RoleApiController {

	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping("/api/roles/custom") 
	public List<Role> runCustomMethod() {
		return roleRepository.getAll();
	}
	
}
