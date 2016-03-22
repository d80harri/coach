package net.d80harri.coach.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.katharsis.resource.registry.ResourceRegistry;
import io.katharsis.spring.boot.KatharsisConfigV2;

@RestController
@SpringBootApplication
@Configuration
@ImportResource("classpath:rest.application-ctx.xml")
@Import({KatharsisConfigV2.class})
public class Application {
	@Autowired
    private ResourceRegistry resourceRegistry;
	
    @RequestMapping("/resourcesInfo")
    public Map<?, ?> getResources() {
        Map<String, String> result = new HashMap<>();
        // Add all resources (i.e. Project and Task)
        for (Class<?> clazz : resourceRegistry.getResources().keySet()) {
           result.put(resourceRegistry.getResourceType(clazz), resourceRegistry.getResourceUrl(clazz));
        }
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
