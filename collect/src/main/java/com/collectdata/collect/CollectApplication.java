package com.collectdata.collect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectApplication.class, args);
	}
/*
* */


	/*
	*
	* spring.datasource.url=jdbc:mysql://mysql:3306/mydata
spring.datasource.username=root
spring.datasource.password=mydb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update*/


	/*
	 * spring.datasource.url=jdbc:mysql://localhost:3306/gradingsystem
	 * spring.datasource.username=root
	 * spring.datasource.password=mysql2023
	 * spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	 * //spring.jpa.hibernate.ddl-auto=update
	 * spring.jpa.show-sql=true
	 * spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
	 * server.servlet.session.timeout=5m*/
}
