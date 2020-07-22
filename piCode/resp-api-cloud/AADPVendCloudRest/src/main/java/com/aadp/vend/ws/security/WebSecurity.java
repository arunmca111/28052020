package com.aadp.vend.ws.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aadp.vend.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
				/*
				 * .antMatchers(HttpMethod.POST, "/products/product").permitAll()
				 * .antMatchers(HttpMethod.GET, "/products/*").permitAll()
				 * .antMatchers(HttpMethod.PATCH, "/products/*").permitAll()
				 * .antMatchers(HttpMethod.DELETE, "/products/*").permitAll()
				 * .antMatchers(HttpMethod.POST, "/orders/order").permitAll()
				 * .antMatchers(HttpMethod.GET, "/orders/*").permitAll()
				 * .antMatchers(HttpMethod.PATCH, "/orders/*").permitAll()
				 * .antMatchers(HttpMethod.DELETE, "/orders/*").permitAll()
				 */
				.antMatchers(SecurityConstants.LOCAL_H2_TEST_URL).permitAll().anyRequest().authenticated().and()
				.addFilter(getAuthenticationFilter())
		        .addFilter(new AuthorizationFilter(authenticationManager()))
		        .sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //need to be removed - for h2 client only.
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	   protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		    filter.setFilterProcessesUrl("/users/login");
		    return filter;
		}


}
