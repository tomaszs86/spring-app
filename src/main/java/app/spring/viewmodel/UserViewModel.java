package app.spring.viewmodel;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import app.spring.form.UserForm;

@Component
public class UserViewModel {

	@Valid
	private UserForm userForm;

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}
}
