package pl.mfurman.owasp_sample.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!secured")
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class UnsecuredConfiguration {
}
