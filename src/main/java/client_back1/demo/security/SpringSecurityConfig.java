package client_back1.demo.security;

import client_back1.demo.security.JWT.JwtEntryPoint;
import client_back1.demo.security.JWT.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@DependsOn("passwordEncoder")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    private JwtEntryPoint accessDenyHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/Doctor/Profil/**").authenticated()
                .antMatchers("/api/Profil/**").authenticated()
                .antMatchers("/api/Doctor/**").access("hasAnyRole('DOCTOR')")
                .antMatchers("/api/provider/**").access("hasAnyRole('PROVIDER')")
                .antMatchers("/api/upload/**").access("hasAnyRole('PROVIDER')")
                .antMatchers("/api/admin/**").access("hasAnyRole('ADMIN ')")
                .antMatchers("/api/product/admin/**").access("hasAnyRole('ADMIN')")
               .antMatchers("/api/Profil/**").access("hasAnyRole('ADMIN', 'PROVIDER')")
                .antMatchers("/api/User/Complaints/**").access("hasAnyRole('ADMIN', 'PROVIDER')")
                .antMatchers("/api/provider/**").authenticated()
                .antMatchers("/api/admin/**").authenticated()
                .antMatchers("/api/Doctor/**").authenticated()
                .antMatchers("/profiles/**").authenticated()
                .anyRequest().permitAll()

               .and()
              .exceptionHandling().authenticationEntryPoint(accessDenyHandler)
               .and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

     http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
