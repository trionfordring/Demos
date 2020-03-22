package icu.fordring.configurer;

import icu.fordring.service.UserVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description
 * @ClassName SecurityConfigurer
 * @Author fordring
 * @date 2020.03.13 13:27
 */
@Slf4j
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Resource(name = "loginSuccessHandle")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource(name = "loginFailureHandle")
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Value("${spring.mvc.static-path-pattern}")
    private String staticPattern;
    @Resource
    private PersistentTokenRepository persistentTokenRepository;
    @Resource
    private UserVerifier userVerifier;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userVerifier).passwordEncoder(passwordEncoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/").loginProcessingUrl("/stranger/login")
                .passwordParameter("password").usernameParameter("name")
                .failureHandler(authenticationFailureHandler).successHandler(authenticationSuccessHandler)
                .and().rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600)
                .tokenRepository(persistentTokenRepository)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/stranger/**").hasAuthority("ANONYMOUS")
                .antMatchers("/tools/hasLogin","/tools/get/authorities").permitAll()
                .antMatchers(staticPattern).permitAll()
                .anyRequest().authenticated()
                .and().anonymous().authorities("ANONYMOUS")
                .and().logout().logoutUrl("/logout")
        ;
    }

}
