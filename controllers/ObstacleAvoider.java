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

/**
* A simple obstacle avoiding controller which uses touch sensors.
* Try it it MazeWorld!
*
* @author Graham Ritchie
*/
public class ObstacleAvoider extends Controller
{
	private AbstractRobot robot;
	private boolean running;

	private int[] sensors={Controller.SENSOR_TYPE_TOUCH,Controller.SENSOR_TYPE_TOUCH,Controller.SENSOR_TYPE_TOUCH};
	
	public void initController(AbstractRobot r)
	{
		robot=r;
	}
	
	public AbstractRobot getRobot()
	{
		return robot;
	}
	
	public int[] getSensors()
	{
		return sensors;
	}
	
	public void run()
	{
		running=true;
		go();
	}
	
	public void halt()
	{
		// stops the robot
		running=false;
		
		robot.stopMoving();
	}
	
	/**
	* Main control loop, continuously checks the touch sensors and 
	* backs the robot up if necessary.
	*/
	public void go()
	{
		// keep looping while running
		while (running)
		{
			// if the touch sensor hits anything, back off and turn round a bit
			
			if (robot.getSensor1()==1)
			{
				robot.backward(500);
				robot.right(1000);
			}
			if (robot.getSensor2()==1)
			{
				robot.backward(500);
				robot.right(1000);
			}
			if (robot.getSensor3()==1)
			{
				robot.backward(500);
				robot.left(1000);
			}
			robot.forward();
			
			try{Thread.sleep(50);}catch(Exception e){}
		}
	}
}	
