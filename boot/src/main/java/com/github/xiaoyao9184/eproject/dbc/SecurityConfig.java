package com.github.xiaoyao9184.eproject.dbc;

import com.xy.spring.security.oauth2.client.OAuth2ClientAuthorizedConfigurer;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by xy on 2021/3/12.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //noinspection unchecked
        http.apply(new OAuth2ClientAuthorizedConfigurer().defaultSuccessUrl("/"));
        // @formatter:off
        http
                .oauth2Client()
                    .and()
                .oauth2Login()
//                    .userInfoEndpoint()
//                        .userAuthoritiesMapper(new Security4UserInfoUriAuthoritiesMapper())
//                        .and()
                    .and()
                .oauth2ResourceServer()
                    .opaqueToken();
        http
                .authorizeRequests()
                    .requestMatchers(EndpointRequest.toAnyEndpoint())
                        .access("hasAuthority('SCOPE_actuate.admin') or hasAnyRole('DEV','ACTUATOR')")
                    .antMatchers("/h2-console/**")
                        .access("hasAnyAuthority('SCOPE_dbc.admin') or hasAnyRole('DEV')")
                    .anyRequest().authenticated()
                    .and()
                .cors().and()
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .httpBasic();
        // @formatter:on
    }

}
