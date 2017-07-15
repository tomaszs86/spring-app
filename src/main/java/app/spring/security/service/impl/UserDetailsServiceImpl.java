package app.spring.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.spring.model.Right;
import app.spring.model.Role;
import app.spring.model.User;
import app.spring.repository.UserRepository;

@Service()
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        
    	User user = userRepository.findByUsername(username);
    	
    	if(user == null) {
    		 throw new UsernameNotFoundException("User was not found"); 
    	}
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, setRights(user.getRoles()));        
    }
    
    private Collection<? extends GrantedAuthority> setRights(Collection<Role> roles) {    	  
    	return getGrantedAuthorities(getRights(roles));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(List<String> rights) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String right : rights) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + right));
        }
        return authorities;
    }
    
    private List<String> getRights(Collection<Role> roles) {
    	  
        List<String> privileges = new ArrayList<>();
        List<Right> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getRights());
        }
        for (Right item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
}

