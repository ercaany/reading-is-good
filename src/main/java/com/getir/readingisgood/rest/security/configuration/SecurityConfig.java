package com.getir.readingisgood.rest.security.configuration;

import com.getir.readingisgood.rest.security.service.FakeUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final FakeUserDetailsService fakeUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  private static final String[] PERMIT_ALL_AUTH_WHITELIST = {
    "/v3/api-docs/**", "/swagger-ui/**", "/auth"
  };

  private static final String[] POST_METHOD_AUTH_WHITELIST = {"/customers"};

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    auth.userDetailsService(fakeUserDetailsService);
  }

  @Override
  public void configure(HttpSecurity security) throws Exception {
    security
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(PERMIT_ALL_AUTH_WHITELIST)
        .permitAll()
        .antMatchers(HttpMethod.POST, POST_METHOD_AUTH_WHITELIST)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
