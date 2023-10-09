package com.kabera.musica;

import com.kabera.musica.property.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties({FileStorageProperty.class})
public class MusicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

}
