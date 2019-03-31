package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    private TrainController controller;
    private TrainUser user;
    private TrainSensor sensor;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void NegativSpeedLimit_AlarmExpected() {
	//Act
        sensor.overrideSpeedLimit(-1);

	//Assert
        verify(user).setAlarmState(true);
    }

    @Test
    public void Over500SpeedLimit_AlarmExpected() {
        //Act
        sensor.overrideSpeedLimit(501);

        //Assert
        verify(user).setAlarmState(true);
    }

    @Test
    public void RelativelyLowSpeedLimit_AlarmExpected() {
	//Arrange
        when(controller.getReferenceSpeed()).thenReturn(100);

        //Act
        sensor.overrideSpeedLimit(49);

        //Assert
        verify(user).setAlarmState(true);
    }

    @Test
    public void AppropriateSpeedLimit_AlarmNotExpected() {
	//Arrange
        when(controller.getReferenceSpeed()).thenReturn(100);

        //Act
        sensor.overrideSpeedLimit(80);

        //Assert
        verify(user).setAlarmState(false);
    }
}
