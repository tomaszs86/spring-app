package app.spring.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class RoleForm {

	private String name;
	
	private Long idRole;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	@NotNull
	@NotEmpty	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
