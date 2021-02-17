package demo.social.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = "demo.social.domain")
@EnableJpaRepositories(basePackages = "demo.social.repos")
@EnableTransactionManagement
public class DomainConfig {
}
