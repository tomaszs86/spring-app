package app.spring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import app.spring.SecurityConfig;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
        super(SecurityConfig.class);
    }

}
