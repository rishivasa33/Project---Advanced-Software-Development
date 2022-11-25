package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.database.Database;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("CSCI5308_15_DEVINT_USER");
        dataSource.setPassword("LZQ7ss9jJS");
        dataSource.setUrl("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_15_DEVINT?enabledTLSProtocols=TLSv1.2");
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");

        Database.instance().setDataSource(dataSource);

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username = ?")
                .authoritiesByUsernameQuery("select username, authority from user_x_role where username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/", "static/css", "static/js").permitAll()
                .and().formLogin();
    }
}
