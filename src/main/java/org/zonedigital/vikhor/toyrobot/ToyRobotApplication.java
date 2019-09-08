package org.zonedigital.vikhor.toyrobot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.zonedigital.vikhor.toyrobot.service.PuppetMaster;


/**
 * The boot class
 * 
 * @author vikhor
 *
 */
@SpringBootApplication
public class ToyRobotApplication implements CommandLineRunner {

	@Autowired
	private PuppetMaster puppetMaster;
	@Autowired
    private ResourceLoader resourceLoader;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ToyRobotApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// if no command line argument is specified then use the embedded (journey.txt) file
		final Resource resource;
		if (args.length != 1 || StringUtils.isEmpty(args[0])) {
			resource = new ClassPathResource("journey.txt");
		} else {
			resource = resourceLoader.getResource("file:"+args[0]);
		}
		puppetMaster.act(resource);
	}

}