package com.mp.ttapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@ComponentScan(basePackages = { "com.mp.ttapi" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
    	.withUser("admin")
        .password("admin")
        .authorities("ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/api/**", "/home").access("hasRole('ROLE_ADMIN')").and().formLogin()
    	
        .loginPage("/login").usernameParameter("username").passwordParameter("password")
        .and().logout().and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout"); 
        
    }
    

    @Bean
    protected BCryptPasswordEncoder encoder() {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder(11);
        return enc;
    }

    
    @Bean
    protected DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        return new DefaultWebSecurityExpressionHandler();
    }
}
    