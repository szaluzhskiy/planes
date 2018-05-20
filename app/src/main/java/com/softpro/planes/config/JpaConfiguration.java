package com.softpro.planes.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Configuration for JPA
 */
@Configuration
@EnableJpaRepositories(basePackages = ProjectProperties.REPOSITORIES_PACKAGE)
public class JpaConfiguration {

}
