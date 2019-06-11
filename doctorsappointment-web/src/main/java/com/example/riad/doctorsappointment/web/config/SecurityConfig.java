package com.example.riad.doctorsappointment.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http
            .authorizeRequests()
            .antMatchers("/","/index","/users/get-allUser")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/users/add-user")
            .permitAll()
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(username -> {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("user with name '" + username + "'not found!");
            }
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        });
    }


    // ------

//    @Autowired
//    private CustomUserService userService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .antMatchers("/appointment-controller", "/doctor")
//            .authenticated()
//            .anyRequest().permitAll()
//            ;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .userDetailsService(username -> {
//                User user = userService.getByUsername(username);
//                if (user == null){
//                    throw new UsernameNotFoundException("user with name '" + username + "'not found!");
//                }
//                return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
//            })
//            .passwordEncoder(encoder());
//    }
//
//    @Bean
//    public PasswordEncoder encoder(){
//       return new StandardPasswordEncoder("53cr3t");
//    }

}


