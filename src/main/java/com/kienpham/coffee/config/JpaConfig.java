package com.kienpham.coffee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.kienpham"})
@EntityScan({"com.kienpham"})
public class JpaConfig {
}
