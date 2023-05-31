package ru.itis.judgeassistant.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.judgeassistant.security.details.UserDetailsServiceImpl;
import ru.itis.judgeassistant.security.filters.AuthenticationFilter;
import ru.itis.judgeassistant.security.filters.AuthorizationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security,
                                                   AuthorizationFilter jwtAuthorizationFilter,
                                                   AuthenticationFilter jwtAuthenticationFilter) throws Exception{
        security.csrf().disable();

        security.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/games/**").permitAll()
                .antMatchers(HttpMethod.GET, "/players/**").permitAll()
                .antMatchers("/score/**").permitAll()
                .antMatchers("/auth/token/**").permitAll()
                .antMatchers("/admins/**").hasAuthority("ADMIN")
                .antMatchers("/control/admin").hasAuthority("ADMIN")
                .antMatchers("/coaches/**").hasAuthority("ADMIN")
                .antMatchers("/control/judge").authenticated()
                .antMatchers("/games/**").authenticated()
                .antMatchers("/courts/**").authenticated()
                .antMatchers("/players/**").authenticated()
                .antMatchers("/scores/**").authenticated()
                .antMatchers("/teams/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/token")
                .usernameParameter("login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/exit", "GET"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        security.addFilter(jwtAuthenticationFilter);
        security.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        builder.authenticationProvider(provider);
    }


}
