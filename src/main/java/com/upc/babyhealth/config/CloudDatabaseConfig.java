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
				.url("jdbc:sap://zeus.hana.prod.eu-central-1.whitney.dbaas.ondemand.com:23803?encrypt=true&validateCertificate=true&currentschema=USR_AIOACBT4W9DI5D1J7MVOAA7XV")
				.username("USR_AIOACBT4W9DI5D1J7MVOAA7XV")
				.password("Ix60cTJeeP6k8ZREh4Y.mqlmRv2STiQguZmkl_1fey5_kzBWOx2YTEGcryaJ7DXSnqf5wdS9r5uDvIBjjtMmYFF55LtaLCmpJGUMVIocHAO0hnjfk8ACBCc6Ip1nf.b-")
				.build();

	}

}
