package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource).
            usersByUsernameQuery("select login,password,true from user where login=?").
             authoritiesByUsernameQuery("select login, role from user where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests()
                .antMatchers("/auth", "/reg","/welcomepage").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/auth").usernameParameter("login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/auth")
                .and().csrf().disable();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/vendor/**");
    }
}
