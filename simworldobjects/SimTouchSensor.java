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

package simworldobjects;

import interfaces.*;

/**
* Simulates a light sensor
*
* @author Graham Ritchie
*/
public class SimTouchSensor extends SimSensor
{
	private SimRCX robot;
	private double xOffset, zOffset;
	
	/**
	* Sets up a light sensor on the given SimRCX, and positions it at the
	* the appropriate offset from the robot's position.
	*
	* @param r the SimRCX owner of this sensor
	* @param xOffset the offest from the SimRCX's x position
	* @param zOffset the offest from the SimRCX's z position
	*/
	public SimTouchSensor(SimRCX r, double xOffset, double zOffset)
	{
		robot=r;
		initSimSensor(r,xOffset,zOffset,10.0,10.0,10.0,"sensor");
		this.xOffset=xOffset;
		this.zOffset=zOffset;
	}
	
	/**
	* Returns the current value of this sensor, a 1 if it is touching something
	* or 0 if it is not.
	*
	* @return 1 or 0 accordingly
	*/
	public int getValue()
	{
		SimWorld world=robot.getWorld();
		
		if (world.hasObstacle(this.getXCoord(),this.getYCoord(),this.getZCoord()))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public double getXOffset()
	{
		return xOffset;
	}
	
	public double getZOffset()
	{
		return zOffset;
	}
}
