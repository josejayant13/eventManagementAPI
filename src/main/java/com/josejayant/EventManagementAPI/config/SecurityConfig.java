package com.josejayant.EventManagementAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.josejayant.EventManagementAPI.services.MyUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
//This bean is for verifying an authentication object.
//Verfication is done against the database.
//DaoAuthenticationProvider is an implementation of this interface. AuthenticationProvider
//    is originally an interface
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//        This method takes UserDetailsService type as an input. Hence created and passed.
//        Creation is happening in MyUserDetailsService class.
        provider.setUserDetailsService(userDetailsService);
//        Need to set the password encoder. So that Spring app is able to decrypt the password fetched
//        from the database
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
//      csrf has been disabled
        http.csrf(customizer -> customizer.disable())
//          Endpoint "/" and any endpoint that starts with "/api/" will not require authentication.
//          Any other request require authentication
            .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/**","/").permitAll()
                        .anyRequest().authenticated())
//            .httpBasic(ustomizer.withDefaults())C
            .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


//

        return http.build();
    }
    
}
