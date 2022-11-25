package dal.csci5308.project.group15.elearning.database;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_15_DEVINT?enabledTLSProtocols=TLSv1.2");
        dataSourceBuilder.username("CSCI5308_15_DEVINT_USER");
        dataSourceBuilder.password("LZQ7ss9jJS");
        return dataSourceBuilder.build();
    }
}