package org.zonedigital.vikhor.toyrobot.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

@RunWith(SpringRunner.class)
public class ReportCommandTest {

	
	@MockBean(name = "reportCommandStream")
	private PrintStream mockedPrintStream;
	@Autowired
	private ReportCommand instance;
	
	
	@Test
	public void testExecute() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
                                                                      .y(3)
                                                                      .facing(FacingEnum.EAST).build();

		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// verifying
		verify(mockedPrintStream, times(1)).println("The robot's position is (2,3), facing EAST.");
		verifyNoMoreInteractions(mockedPrintStream);
	}
	
	
	@Configuration
    @Import(ReportCommand.class)
    static class Config {
	}	
	
}
