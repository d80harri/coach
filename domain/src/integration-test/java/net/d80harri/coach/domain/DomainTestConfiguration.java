package net.d80harri.coach.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DomainConfiguration.class })
public class DomainTestConfiguration {

}
