package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.security.encoder.AuraPasswordEncoder;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Value("${aura.db.max.idle}")
    private int dbMaxIdle;

    @Value("${aura.db.initial.size}")
    private int dbInitialSize;

    @Value("${aura.db.validation.query}")
    private String dbValidationQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dbConnector);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setUrl(dbUrl);
        dataSource.setMaxIdle(dbMaxIdle);
        dataSource.setInitialSize(dbInitialSize);
        dataSource.setValidationQuery(dbValidationQuery);

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
                .antMatchers("/forum/**").hasAnyAuthority("admin", "student", "professor", "superuser")
                .antMatchers("/registerUser/**").hasAuthority("admin")
                .antMatchers("/student/**").hasAnyAuthority("student", "admin")
                .antMatchers("/professor/**").hasAnyAuthority("professor", "admin")
                .antMatchers("/professor/courseDetails/fetchModuleContentFile").permitAll()
                .antMatchers("/", "static/css", "static/js", "/login").permitAll()
                .and().formLogin();
    }
}
