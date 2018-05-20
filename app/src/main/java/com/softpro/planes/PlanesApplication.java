package com.softpro.planes;

import com.softpro.planes.config.ProjectProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Launcher for spring boot application.
 */
@SpringBootApplication
@ComponentScan(ProjectProperties.BASE_PACKAGE)
@EntityScan( basePackages = {ProjectProperties.MODELS_PACKAGE} )
public class PlanesApplication {
    /**
     * Start application.
     *
     * @param args arguments
     */
    public static void main(String... args) {
        SpringApplication.run(PlanesApplication.class, args);
    }
}


