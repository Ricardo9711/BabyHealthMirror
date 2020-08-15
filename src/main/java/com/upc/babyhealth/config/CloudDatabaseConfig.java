package com.upc.babyhealth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {
	
	@Bean
	@Primary
	public DataSource dataSource(@Value("${hana.url}")final String url,
			@Value("${hana.user}")final String user,
			@Value("${hana.password}")final String password) {
		
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.driverClassName(com.sap.db.jdbc.Driver.class.getName())
				.url("jdbc:sap://zeus.hana.prod.us-east-1.whitney.dbaas.ondemand.com:21022?encrypt=true&validateCertificate=true&currentschema=USR_1WM0MYA9H9Y8MAD8QSCAGGUQ9")
				.username("USR_1WM0MYA9H9Y8MAD8QSCAGGUQ9")
				.password("Qh2k0_EDw2SK-UhJsxkvr58HpGAK4JdkCxv3oHDhSnEQF61AetuVOh-o6KrNeto..SjDVP3YtsTQAURx9AOu1.XpeVwikgcGuoroOf1bCE4yRNSuhpmzMZshSahvouVZ")
				.build();

	}

}
