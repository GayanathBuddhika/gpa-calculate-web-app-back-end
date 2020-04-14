package com.gpastm.gpa;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gpastm.gpa.filters.JwtRequestFilters;
import com.gpastm.gpa.model.AuthenticationRequest;
import com.gpastm.gpa.model.AuthenticationResponse;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.repository.UserRepository;
import com.gpastm.gpa.service.UserService;
import com.gpastm.gpa.util.JwtUtil;





@SpringBootApplication
public class GpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpaApplication.class, args);
	}
	
//	 @Bean
//	    public WebMvcConfigurer corsConfigurer() 
//	    {
//	        return new WebMvcConfigurer() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST","PUT", "DELETE");
//	            }
//	        };
//	    }
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept",
				"Authorization", "X-Authorization-Firebase"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;

}
	
	@RestController
	class HelloWorldController {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private JwtUtil jwtTokenUtil;
		
		@Autowired
		private UserService userService;

		@Autowired
		private MyUserDetailsService userDetailsService;

		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<User> firstPage(@RequestBody AuthenticationRequest request ){
			
			User user = userService.findByEmail(request.getUsername()).get();
			
			user.setPassword(request.getPassword());
			
			User usersaved = userService.adduser(user);
			
			System.out.println("register user " + usersaved.getName());
			
			return new ResponseEntity<User>(usersaved, HttpStatus.OK);
		}


		@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
		public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
			}
			catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}


			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);
			
			Optional<User> user = userService.findByEmail(authenticationRequest.getUsername());

			//return ResponseEntity.ok(new AuthenticationResponse(jwt, user.get()));
			AuthenticationResponse re = new AuthenticationResponse(jwt, user.get());
			return new ResponseEntity<AuthenticationResponse>(re, HttpStatus.OK);
		}

	}
	
	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService myUserDetailsService;
		
		@Autowired
		private JwtRequestFilters jwtRequestFilter;
		
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(myUserDetailsService);
		}


		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
		
		


		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
//		 @Override
//		    protected void configure(HttpSecurity http) throws Exception {
//		        // ...
//		        http.cors();
//		    }
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
//			httpSecurity.csrf().disable()
//					.authorizeRequests().antMatchers("/authenticate").permitAll().
//							anyRequest().authenticated().and().
//							exceptionHandling().and().sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			httpSecurity.cors();
			httpSecurity.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate","/register").permitAll().
					anyRequest().authenticated().and().
					exceptionHandling().and().sessionManagement()
			       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	          httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


		}
	}
}
