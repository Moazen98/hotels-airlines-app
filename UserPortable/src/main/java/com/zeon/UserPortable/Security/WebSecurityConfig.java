//package com.zeon.UserPortable.Security;
//
//
//import javax.sql.DataSource; 
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandlerImpl;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	DataSource dataSource;
//
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select username,password, enabled from teacherinfo where username=?")
//				.authoritiesByUsernameQuery("select username,roles from teacherroles where username=?");
////		 auth
////       .inMemoryAuthentication()
////           .withUser("admin").password(encoder().encode("123")).roles("ADMIN").and()
////           .withUser("user").password(encoder().encode("123")).roles("USER");
//
//	}
//
//	  @Override
//	  protected void configure(HttpSecurity http) throws Exception {
////		  http 
////	        .csrf() 
////	        .disable() 
////	    .authorizeRequests()
////	    .antMatchers("/welcome").hasRole("ADMIN")
////	    .antMatchers("/register-page").permitAll()	
////	    .antMatchers("/showallteacher").permitAll()	
////	    .antMatchers("/register").permitAll()
////	        .anyRequest().authenticated().and().formLogin().permitAll().
////	        defaultSuccessUrl("/welcome", false)
////	        .and().logout()
////	        .permitAll()
////	        .and()
////	        .exceptionHandling()
////            .accessDeniedHandler((request, response, accessDeniedException) -> {
////                AccessDeniedHandler defaultAccessDeniedHandler = new AccessDeniedHandlerImpl();
////                defaultAccessDeniedHandler.handle(request, response, accessDeniedException);
////            });
//		  http 
//			.csrf() 
//			.disable()
//			.authorizeRequests()
//			.antMatchers("/welcome").permitAll()
//			.antMatchers("/showallteacher").hasAnyAuthority("ADMIN")
//			.anyRequest().authenticated().and().formLogin().permitAll()
//			 .defaultSuccessUrl("/username", true)
//			 .and().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) .logoutSuccessUrl("/login").permitAll();
//				http.exceptionHandling().accessDeniedPage("/403");
//	 
//	  }
//	  
//	  public static PasswordEncoder encoder() {
//			return new BCryptPasswordEncoder();
//		}
//	  
//	  @SuppressWarnings("deprecation")
//	  @Bean
//	  public static NoOpPasswordEncoder passwordEncoder() {
//	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	  }
//}