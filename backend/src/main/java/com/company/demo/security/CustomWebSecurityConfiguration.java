package com.company.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.demo.jwt.JwtAuthenticationEntryPoint;
import com.company.demo.jwt.JwtAuthenticationFilter;
import com.company.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String H2 = "/h2db/**";

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.csrf().ignoringAntMatchers(H2).and().headers().frameOptions().sameOrigin();
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpHeaders.ALLOW).permitAll()
				.antMatchers(H2, "/api/auth/**", "/api/user/register").permitAll().antMatchers("/",
						"/api/book/fetch/**", "/api/book/fetch-all", "/api/author/fetch/**", "/api/author/fetch-all")
				.permitAll().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(10);
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
