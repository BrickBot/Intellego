/*

Copyright 2002 Graham Ritchie <grr@dcs.st-and.ac.uk>

This file is part of Intellego.

Intellego is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Intellego is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Intellego; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

import interfaces.Controller;
import interfaces.AbstractRobot;

import java.lang.*;
import java.util.*;

/**
* This is the hand-coded light seeking controller
*
* @author Graham Ritchie
*/
public class HandCodedLightSeeker extends Controller
{
	private AbstractRobot robot;
	private boolean running;

	private int[] sensors={Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT};
	
	/************************************************
	           Methods requird by Controller
	*************************************************/
	
	public void initController(AbstractRobot r)
	{
		robot=r;
	}
	
	public int[] getSensors()
	{
		return sensors;
	}
	
	public void halt()
	{
		// set running to false, this will force run() to return, and therefore 
		// stop the controller's thread
		running=false;
		
		// stop the robot also
		robot.stopMoving();
	}
	
	public AbstractRobot getRobot()
	{
		return robot;
	}
	
	public void run()
	{
		// set running to true
		running=true;
		
		// loop continuously while running
		while (running)
		{
			// Main mapping - takes the sensor values at each step and comands the
			// robot to do the appropriate
			
			if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){robot.left();}
			if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){robot.left();}
			if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){robot.forward();}
			if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){robot.right();}
			
			try{sleep(500);}catch(Exception e){}
		}
	}
}
