package me.espacodosautores.login.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.espacodosautores.login.model.SecUser;
import me.espacodosautores.login.service.SecUserService;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private SecUserService userService;

	private User springUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		SecUser user = userService.findByUsername(username);
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		if (user != null) {
			springUser = new User(user.getUsername(), user.getPassword(),enabled, 
					accountNonExpired, credentialsNonExpired, accountNonLocked, authList);
			return springUser;
		} else {
			springUser = new User("empty", "empty", false, true, true, false, authList);
			return springUser;
		}
	}
}
