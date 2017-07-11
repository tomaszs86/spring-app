package app.spring.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
