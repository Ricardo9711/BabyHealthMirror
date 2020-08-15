package com.upc.babyhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//(exclude={DataSourceAutoConfiguration.class})

@SpringBootApplication
public class BabyhealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabyhealthApplication.class, args);
	}

}
