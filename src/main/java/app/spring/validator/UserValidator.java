package app.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import app.spring.form.UserForm;
import app.spring.model.User;
import app.spring.repository.UserRepository;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserForm.class.equals(aClass);
	}
	
    @Override
    public void validate(Object o, Errors errors) {
        
    	UserForm user = (UserForm) o;

        /*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }        
        */
        
        if (userRepository.findByUsername(user.getUsername()) != null) {
        	errors.rejectValue("userForm.username", "Duplicate.userForm.username");
        }


    }
	

}
