package app.spring.viewmodel;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import app.spring.form.LoginForm;

@Component
public class LoginViewModel {

	@Valid
	private LoginForm loginForm;

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
}
