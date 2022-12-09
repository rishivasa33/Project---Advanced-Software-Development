package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.database.Database;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.SecureRandom;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Value("${aura.db.connector}")
    private String dbConnector;

    @Value("${aura.db.url}")
    private String dbUrl;

    @Value("${aura.db.username}")
    private String dbUsername;

    @Value("${aura.db.password}")
    private String dbPassword;

    @Value("${aura.db.query.user.authentication}")
    private String userAuthenticationQuery;

    @Value("${aura.db.query.user.authorization}")
    private String userAuthorizationQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dbConnector);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setUrl(dbUrl);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");

        Database.instance().setDataSource(dataSource);

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userAuthenticationQuery)
                .authoritiesByUsernameQuery(userAuthorizationQuery);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return AuraPasswordEncoder.instance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .antMatchers("/user").hasAnyAuthority("USER")
                .antMatchers("/forum/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/", "static/css", "static/js").permitAll()
                .and().formLogin();
    }
}
