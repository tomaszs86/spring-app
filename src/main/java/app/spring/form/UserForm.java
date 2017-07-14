package app.spring.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	private Long idUser;
	private String username;
	private String password;
	
	public Long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	@NotNull
	@NotEmpty
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@NotEmpty
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
	
	
	
}
