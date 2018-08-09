package com.coffeeshop.config;

import com.coffeeshop.authentication.form.CustomFormAuthenticationFilter;
import com.coffeeshop.authentication.form.FormAuthenticationProvider;
import com.coffeeshop.authentication.jwt.JwtAuthenticationFilter;
import com.coffeeshop.authentication.jwt.JwtAuthenticationProvider;
import com.coffeeshop.authentication.service.CustomUserDetailsService;
import com.coffeeshop.config.handler.CustomAccessDeniedHandler;
import com.coffeeshop.config.handler.CustomAuthenticationEntryPoint;
import com.coffeeshop.config.handler.CustomLogoutSuccessfulHandler;
import com.coffeeshop.service.common.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security Configuration
 * Adding custom filter to customize default process of
 * Authentication in spring security.
 * @author Chandan Vishwakarma
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomLogoutSuccessfulHandler logoutSuccessfulHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Autowired
    private FormAuthenticationProvider formAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CustomHeaderFilterConfig customHeaderFilterConfig;

    protected CustomFormAuthenticationFilter getCustomAuthenticationFilter(String pattern) {
        CustomFormAuthenticationFilter customAuthenticationFilter =
                new CustomFormAuthenticationFilter(new AntPathRequestMatcher(pattern), userDetailsService, jwtSecretKey,messageService);
        customAuthenticationFilter.setAuthenticationManager(this.authenticationManager);
        return customAuthenticationFilter;
    }

    protected JwtAuthenticationFilter getJwtAuthenticationFilter(String pattern) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(new AntPathRequestMatcher(pattern), this.jwtSecretKey);
        jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManager);
        return jwtAuthenticationFilter;

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.authenticationProvider(formAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider)
                .userDetailsService(userDetailsService);
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable()
                    .formLogin()
                    .loginProcessingUrl("/api/login")
                    .and()
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutUrl("/auth/logout")
                    .logoutSuccessHandler(logoutSuccessfulHandler)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/login").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/api/staff/**").access("hasRole('STAFF')")
                    .and()
                    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .addFilterBefore(new CORSFilterConfig(), ChannelProcessingFilter.class)
                    .addFilterBefore(customHeaderFilterConfig, ChannelProcessingFilter.class)
                    .addFilterBefore(getCustomAuthenticationFilter("/api/login"), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(getJwtAuthenticationFilter("/api/admin/**"), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(getJwtAuthenticationFilter("/api/staff/**"), UsernamePasswordAuthenticationFilter.class)
                    .anonymous()
                    .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
                web
                    .ignoring()
                        .antMatchers(
                                "/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/webjars/**"
                        );
    }
}
