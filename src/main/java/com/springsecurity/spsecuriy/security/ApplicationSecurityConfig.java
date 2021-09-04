package com.springsecurity.spsecuriy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.springsecurity.spsecuriy.security.AppUserPermission.COURSE_WRITE;
import static com.springsecurity.spsecuriy.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")  .permitAll()
                .antMatchers("/api/*").hasAnyRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/management/**").hasAnyAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/management/**").hasAnyAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST,"/api/v1/management/**").hasAnyAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET,"/api/v1/management/**").hasAnyRole(ADMIN.name(),TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails admin= User.builder()
               .username("admin")
               .password(getPasswordEncoder().encode("admin"))
              // .roles(ADMIN.name())
               .authorities(ADMIN.getGrantedAuthorities())
               .build();
        UserDetails trainee= User.builder()
                .username("trainee")
                .password(getPasswordEncoder().encode("admin"))
              //  .roles(TRAINEE.name())
                .authorities(TRAINEE.getGrantedAuthorities())
                .build();
        UserDetails priaynshu= User.builder()
                .username("priya")
                .password(getPasswordEncoder()
                        .encode("admin"))
             //   .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())

                .build();

        return new InMemoryUserDetailsManager(admin,priaynshu,trainee);
    }
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return   new BCryptPasswordEncoder();
    }
}
