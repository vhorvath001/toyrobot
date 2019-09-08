package org.zonedigital.vikhor.toyrobot;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zonedigital.vikhor.toyrobot.service.PuppetMaster;


@RunWith(SpringRunner.class)
public class SimulationIntTest {

	
	@Autowired
	private PuppetMaster puppetMaster;
	
	@MockBean(name = "reportCommandStream")
	private PrintStream mockedPrintStream;
	
	
	@Test
	public void roamingAround_1() throws URISyntaxException, IOException {
		Resource resource = new ClassPathResource("journey_1.txt");
		puppetMaster.act(resource);
		
		// verifying
		verify(mockedPrintStream,times(1)).println("The robot's position is (0,0), facing WEST.");
		verify(mockedPrintStream,times(1)).println("The robot's position is (1,1), facing EAST.");
	}
	
	
	@Test
	public void roamingAround_2() throws URISyntaxException, IOException {
		Resource resource = new ClassPathResource("journey_2.txt");
		puppetMaster.act(resource);
		
		// verifying
		verify(mockedPrintStream,times(1)).println("The robot's position is (0,0), facing WEST.");
	}

	
	@Configuration
	@ComponentScan
    @Import(PuppetMaster.class)
    static class Config {
	}
	
}
