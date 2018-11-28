package com.myrepublic.numbermanage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @date 2018/11/25
 * @desc security authenticate class
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("admin")).roles("ADMIN","USER").and()
				.withUser("user").password(encoder().encode("user")).roles("USER");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/getAllUsers").hasRole("ADMIN")
				.antMatchers("/getAllMobiles").hasRole("ADMIN")
				.antMatchers("/getAllServices").hasRole("USER")
				.antMatchers("/getAvailableMobiles").hasRole("USER")
				.antMatchers("/getCurrentStatus").hasRole("ADMIN")
				.antMatchers("/getMobileHistory").hasRole("ADMIN")
				.antMatchers("/bindNumber").hasRole("USER")
				.antMatchers("/unBindNumber").hasRole("USER")
				.antMatchers("/bindService").hasRole("USER")	
				.antMatchers("/unBindService").hasRole("USER")		
				.antMatchers("/getUserFullPicture").hasRole("USER")	
				.antMatchers("/error").permitAll()
				.antMatchers("/**").hasRole("USER")
				.and().csrf().disable().headers().frameOptions().disable();
	
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}