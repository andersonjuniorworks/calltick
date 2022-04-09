package com.andersonjunior.calltick.config;

import java.util.Arrays;

import com.andersonjunior.calltick.security.JwtAuthenticationFilter;
import com.andersonjunior.calltick.security.JwtAuthorizationFilter;
import com.andersonjunior.calltick.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private Environment env;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] PUBLIC_MATCHERS = {
        "/h2-console/**",
        "/notify/**",
        "/disconnected/**",
        "/connected/**",
        "/usersConnected/**",
        "/getTickets/**",
        "/info/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
        "/getUsers/**",
        "/api/clients/count/**",
        "/api/users/count/**",
        "/api/sectors/**",
        "/info"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/auth/forgot/**"
    };

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS", "PATCH"));        
        configuration.setAllowCredentials(false);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
        .antMatchers(PUBLIC_MATCHERS).permitAll()
        .antMatchers(PUBLIC_MATCHERS_POST).permitAll()
        .anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*     @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .maxAge(3600L)
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(false);
    }
 */

}
