//package org.raindrop.auth.config;
//
//import lombok.SneakyThrows;
//import org.raindrop.common.security.handle.FormAuthenticationFailureHandler;
//import org.raindrop.common.security.handle.MobileLoginSuccessHandler;
//import org.raindrop.common.security.handle.SsoLogoutSuccessHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    @SneakyThrows
//    protected void configure(HttpSecurity http) {
//        http.formLogin().loginPage("/token/login").loginProcessingUrl("/token/form")
//                .failureHandler(authenticationFailureHandler()).and().logout()
//                .logoutSuccessHandler(logoutSuccessHandler()).deleteCookies("JSESSIONID").invalidateHttpSession(true)
//                .and().authorizeRequests().antMatchers("/token/**", "/actuator/**", "/mobile/**").permitAll()
//                .anyRequest().authenticated().and().csrf().disable().apply(mobileSecurityConfigurer());
//    }
//
//    /**
//     * 不拦截静态资源
//     * @param web
//     */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/error");
//    }
//
//
//    @Bean
//    @Override
//    @SneakyThrows
//    public AuthenticationManager authenticationManagerBean() {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new FormAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new SsoLogoutSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler mobileLoginSuccessHandler() {
//        return new MobileLoginSuccessHandler();
//    }
//
//    @Bean
//    public MobileSecurityConfigurer mobileSecurityConfigurer() {
//        return new MobileSecurityConfigurer();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}
