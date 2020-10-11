package com.upc.babyhealth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {
	
	@Bean
	public DataSource dataSource(@Value("${hana.url}")final String url,
			@Value("${hana.user}")final String user,
			@Value("${hana.password}")final String password) {
		
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.driverClassName(com.sap.db.jdbc.Driver.class.getName())
				.url("jdbc:sap://534f8e40-5a9d-46cd-ad31-7247ebf38a5f.hana.trial-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true&currentschema=USR_BMSJMNBWF1VRMUEEZV6ZIXZ6O")
				.username("USR_BMSJMNBWF1VRMUEEZV6ZIXZ6O")
				.password("Nh5vA6RsAPtygG_4gg8aSmEM.mbNGXjfnHL0K6JuHhBrRxA3bgGUBry3xUtjbbPl-i6.rnVv5WnQaW5dz7m3.fhtPMbDQWiAIA6vqPhDsK7p2tpn5BwC3ahMntuNQJWy")
				.build();

	}

}
