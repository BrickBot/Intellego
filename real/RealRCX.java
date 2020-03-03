package real;

import interfaces.*;

import josx.platform.rcx.*;

/**
* This class implements the simple commands provided by AbstractRobot with
* real lejos commands, allowing the simple commands to be run on the 
* real RCX.
*
* @author Graham Ritchie
*/
public class RealRCX extends Thread implements AbstractRobot, SensorConstants
{
	public static int MOTOR_POWER;
	public void run(){}
	
	/**
	* Initialises necessary variables, and sets up sensors, currently fixed to:
	*
	* @param controller the Controller associated with this RCX 
	*/
	public RealRCX(Controller controller)
	{
		MOTOR_POWER=3;
		Motor.A.setPower(MOTOR_POWER);
		Motor.B.setPower(MOTOR_POWER);
		Motor.C.setPower(MOTOR_POWER);
		
		// set up sensors as specified in controller
		int[] sensors=controller.getSensors();
		
		if(sensors[0]==Controller.SENSOR_TYPE_LIGHT)
		{
			Sensor.S1.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S1.activate();
		}
		else if(sensors[0]==Controller.SENSOR_TYPE_TOUCH)
		{
			Sensor.S1.setTypeAndMode (SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S1.activate();
		}
		
		if(sensors[1]==Controller.SENSOR_TYPE_LIGHT)
		{
			Sensor.S2.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S2.activate();
		}
		else if(sensors[1]==Controller.SENSOR_TYPE_TOUCH)
		{
			Sensor.S2.setTypeAndMode (SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S2.activate();
		}
		
		if(sensors[2]==Controller.SENSOR_TYPE_LIGHT)
		{
			Sensor.S3.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S3.activate();
		}
		else if(sensors[2]==Controller.SENSOR_TYPE_TOUCH)
		{
			Sensor.S3.setTypeAndMode (SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_PCT);
			Sensor.S3.activate();
		}
	}
	
	
	/*******************************************************************
	                Methods required by AbstractRobot
	*******************************************************************/
	
	/**
	* Sets the robot moving forwards, this will continue until some other 
	* method is called to stop it.
	*/
	public void forward()
	{
		Motor.A.forward();
		Motor.C.forward();
	}
	
	/**
	* Makes the robot move forwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void forward(int time)
	{
		Motor.A.forward();
		Motor.C.forward();
		try{sleep(time);}catch(Exception e){}
		Motor.A.stop();
		Motor.C.stop();
	}
	
	/**
	* Sets the robot moving backwards, this will continue until some other 
	* method is called to stop it.
	*/
	public void backward()
	{
		Motor.A.backward();
		Motor.C.backward();
	}
	
	/**
	* Makes the robot move backwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void backward(int time)
	{
		Motor.A.backward();
		Motor.C.backward();
		try{sleep(time);}catch(Exception e){}
		Motor.A.stop();
		Motor.C.stop();
	}
	
	/**
	* Sets the robot spinning right, this will continue until some other 
	* method is called to stop it.
	*/
	public void right()
	{
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.forward();
		Motor.C.backward();
	}
	
	/**
	* Spins the robot right for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void right(int time)
	{
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.forward();
		Motor.C.backward();
		try{sleep(time);}catch(Exception e){}
		Motor.A.stop();
		Motor.C.stop();
	}
	
	/**
	* Sets the robot spinning left, this will continue until some other 
	* method is called to stop it.
	*/
	public void left()
	{
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.backward();
		Motor.C.forward();
	}
	
	/**
	* Spins the robot left for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void left(int time)
	{
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.backward();
		Motor.C.forward();
		try{sleep(time);}catch(Exception e){}
		Motor.A.stop();
		Motor.C.stop();
	}
	
	/**
	* Stops all motors immediately
	*/
	public void stopMoving()
	{
		Motor.A.stop();
		Motor.C.stop();
	}
	
	/**
	* Makes the robot beep
	*/
	public void beep()
	{
		Sound.beep();
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor1()
	{
		return Sensor.S1.readValue();
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor2()
	{
		return Sensor.S2.readValue();
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor3()
	{
		return Sensor.S3.readValue();
	}
}
