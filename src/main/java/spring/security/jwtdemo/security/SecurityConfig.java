package spring.security.jwtdemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.security.jwtdemo.security.filters.FormLoginFilter;
import spring.security.jwtdemo.security.filters.JwtAuthenticationFilter;
import spring.security.jwtdemo.security.filters.SocialLoginFilter;
import spring.security.jwtdemo.security.handlers.FormLoginAuthenticationFailuerHandler;
import spring.security.jwtdemo.security.handlers.FormLoginAuthenticationSuccessHandler;
import spring.security.jwtdemo.security.handlers.JwtAuthenticationFailureHandler;
import spring.security.jwtdemo.security.providers.FormLoginAuthenticationProvider;
import spring.security.jwtdemo.security.providers.JwtAuthenticationProvider;
import spring.security.jwtdemo.security.providers.SocialLoginAuthenticationProvider;
import spring.security.jwtdemo.security.services.SocialFetchServiceProd;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // can use Spring AOP to authorize at method level
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;

    @Autowired
    private FormLoginAuthenticationFailuerHandler formLoginAuthenticationFailuerHandler;

    @Autowired
    private FormLoginAuthenticationProvider formLoginAuthenticationProvider;

    @Autowired
    private SocialLoginAuthenticationProvider socialLoginAuthenticationProvider;

    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Autowired
    private HeaderTokenExtractor extractor;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    private FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter filter = new FormLoginFilter("/formLogin", formLoginAuthenticationSuccessHandler, formLoginAuthenticationFailuerHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    private SocialLoginFilter socialLoginFilter() throws Exception {
        SocialLoginFilter filter = new SocialLoginFilter("/socialLogin", formLoginAuthenticationSuccessHandler, formLoginAuthenticationFailuerHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // This would normally belong somewhere else.
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected JwtAuthenticationFilter jwtFilter() throws Exception {
        FilterSkipMatcher matcher = new FilterSkipMatcher(Arrays.asList("/formLogin", "/socialLogin", "*/resources/**"), "/api/**");
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(matcher, jwtAuthenticationFailureHandler, extractor);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Override // Add provider.
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(formLoginAuthenticationProvider)
                .authenticationProvider(jwtAuthenticationProvider)
                .authenticationProvider(socialLoginAuthenticationProvider);
    }

    @Override // Add filter.
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable session usage.
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console**").permitAll()
                .and()
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class) // add filter before spring's default initial filter.
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(socialLoginFilter(), UsernamePasswordAuthenticationFilter.class
                );
    }
}
