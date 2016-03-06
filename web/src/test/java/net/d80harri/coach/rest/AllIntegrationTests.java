package net.d80harri.coach.rest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.googlecode.junittoolbox.ParallelSuite;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(ParallelSuite.class)
@SuiteClasses({"**/*IT.class"})
public class AllIntegrationTests {

	static ConfigurableApplicationContext ctx; 
	@BeforeClass
	public static void init() {
		ctx = SpringApplication.run(Application.class);
	}

	@AfterClass
	public static void destroy() {
		ctx.close();
	}
}
