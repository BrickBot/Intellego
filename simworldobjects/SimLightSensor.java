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
public class SimLightSensor extends SimSensor
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
	public SimLightSensor(SimRCX r, double xOffset, double zOffset)
	{
		robot=r;
		initSimSensor(r,xOffset,zOffset,10.0,10.0,10.0,"sensor");
		this.xOffset=xOffset;
		this.zOffset=zOffset;
	}
	
	/**
	* Returns the current brightness of the world at this sensor's position
	*
	* @return the light level as an int
	*/
	public int getValue()
	{
		// ask the SimWorld for it's brightness at this point, and return it
		SimWorld world=robot.getWorld();
		return world.getBrightness(this.getXCoord(),this.getYCoord(),this.getZCoord());
	}
	
	/**
	* Returns the offset from the owning SimRCX's x coordinate
	*
	* @return the x offset
	*/
	public double getXOffset()
	{
		return xOffset;
	}
	
	/**
	* Returns the offset from the owning SimRCX's y coordinate
	*
	* @return the y offset
	*/
	public double getZOffset()
	{
		return zOffset;
	}
}
