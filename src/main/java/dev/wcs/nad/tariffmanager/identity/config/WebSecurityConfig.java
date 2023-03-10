package dev.wcs.nad.tariffmanager.identity.config;

import dev.wcs.nad.tariffmanager.identity.user.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SecurityUserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(SecurityUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        // Only allow frames if using h2 as the database for console
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests()
            .requestMatchers("/public/*.html").denyAll()
                // add authentication & authorization for admin, backoffice and user settings here
            .requestMatchers("/public/**", "/", "/webjars/**", "/api/**", "/v3/**", "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**")
            .permitAll()
            .anyRequest()
            .authenticated()
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
