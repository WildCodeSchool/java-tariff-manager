package dev.wcs.nad.tariffmanager.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dev.wcs.nad.tariffmanager.identity.user.SecurityUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SecurityUserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(SecurityUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Profile("tests")
	@Bean
	public SecurityFilterChain filterChainTest(HttpSecurity http) throws Exception {
        System.out.println("enable TEST security configuration");
        http.csrf().disable();
        http
            .authorizeHttpRequests()
            .anyRequest().permitAll();


        return http.build();
    }

    @Profile("!tests")
    @Bean
	public SecurityFilterChain filterChainNormal(HttpSecurity http) throws Exception {
        System.out.println("enable NORMAL security configuration");

        http.csrf().disable();
        // Only allow frames if using h2 as the database for console
        http.headers().frameOptions().disable();
        http
            .authorizeHttpRequests()
            .requestMatchers("/public/admin")
                .hasAnyRole("ADMIN")
            .requestMatchers("/public/customer/**")
                .hasAnyRole("ADMIN", "BACKOFFICE")
            .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("/public/sign-in").permitAll()
                .loginProcessingUrl("/public/do-sign-in")
                .defaultSuccessUrl("/public/restricted/view")
                .failureUrl("/public/sign-in?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
        .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/public/logout"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/public/index")
                .deleteCookies("JSESSIONID");


        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoOverride();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
