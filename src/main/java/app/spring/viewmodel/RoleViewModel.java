package app.spring.viewmodel;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import app.spring.form.RoleForm;
import app.spring.model.Role;

@Component
public class RoleViewModel {

	public RoleViewModel() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	}
	private RoleForm roleForm;
	
	private List<Role> roles;
	
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleForm getRoleForm() {
		return roleForm;
	}

	public void setRoleForm(RoleForm roleForm) {
		this.roleForm = roleForm;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
