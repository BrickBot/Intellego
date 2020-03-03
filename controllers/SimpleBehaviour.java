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
* A simple example behaviour - used for testing, debugging etc,
*
* @author Graham Ritchie
*/
public class SimpleBehaviour extends Controller
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
	}
	
	/**
	* Control method - just moves the robot around a bit
	*/
	public void go()
	{
		robot.right(4500);
		
		robot.forward(12000);
		
		robot.backward(12000);
	}
}	
