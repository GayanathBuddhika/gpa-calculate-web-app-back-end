package com.gpastm.gpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.MyUserDetails;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.repository.UserRepository;
import com.gpastm.gpa.service.UserService;


@Service 
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;

	 @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

	    	Optional<User> user = userService.findByEmail(userName);
	    	System.out.println("**************" + user.get().getEmail());
	    	user.orElseThrow(() -> new UsernameNotFoundException("Not Found " + userName));
	    	return user.map(MyUserDetails :: new).get();
	    	//        return new User("foo", "foo",
//	                new ArrayList<>());
	    }
}
