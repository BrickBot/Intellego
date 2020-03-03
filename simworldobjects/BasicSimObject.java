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
* Abstract class for SimObjects, provides a base implementation of 
* the SimObject interface which more complex objects can extend. (e.g
* SimRCX and SimLight).
*
* @see interfaces.SimObject
* 
* @author Graham Ritchie
*/
public abstract class BasicSimObject implements SimObject
{
	// position variables
	private double desiredVelocity;
	private double actualVelocity;
	private double xCoord;
	private double yCoord;		
	private double zCoord;
	
	// bearing variables
	private double desiredBearingVelocityXZ; // +x: clockwise, -x: anticlockwise
	private double desiredBearingVelocityXY; // +x: clockwise, -x: anticlockwise
	private double actualBearingVelocityXZ;
	private double actualBearingVelocityXY;
	private double actualBearingXZ;
	private double actualBearingXY;
	
	// physical properties (must be set in constructor)
	private double height; 	// y
	private double width;	// z
	private double length;	// x
	
	// type property (must be set in constructor)
	private String type; // permitted: light, robot, wall, sensor, floor
	
	public BasicSimObject(double height, double width, double length, String type, double x, double y, double z, double bearingXY, double bearingXZ)
	{
		// initialise all variables
		this.height=height;
		this.width=width;
		this.length=length;
		this.type=type;
		this.xCoord=x;
		this.yCoord=y;
		this.zCoord=z;
		this.actualBearingXZ=bearingXZ;
		this.actualBearingXY=bearingXY;
		
		this.desiredVelocity=0;
		this.actualVelocity=0;
		
		this.desiredBearingVelocityXZ=0;
		this.desiredBearingVelocityXY=0;
		this.actualBearingVelocityXZ=0;
		this.actualBearingVelocityXY=0;
	}
	
	// get and set methods for all variables

	public void setDesiredVelocity(double v)
	{
		this.desiredVelocity=v;
	}
	
	public void setActualVelocity(double v)
	{
		this.actualVelocity=v;
	}
	
	public void setXCoord(double x)
	{
		this.xCoord=x;
	}
	
	public void setYCoord(double y)
	{
		this.yCoord=y;
	}
	
	public void setZCoord(double z)
	{
		this.zCoord=z;
	}
	
	public double getDesiredVelocity()
	{
		return this.desiredVelocity;
	}
	
	public double getActualVelocity()
	{
		return this.actualVelocity;
	}
	
	public double getXCoord()
	{
		return this.xCoord;
	}
	
	public double getYCoord()
	{
		return this.yCoord;
	}
	
	public double getZCoord()
	{
		return this.zCoord;
	}
	
	public void setDesiredBearingVelocityXZ(double v)
	{
		this.desiredBearingVelocityXZ=v;
	}
	
	public void setDesiredBearingVelocityXY(double v)
	{
		this.desiredBearingVelocityXY=v;
	}
	
	public void setActualBearingVelocityXZ(double b)
	{
		this.actualBearingVelocityXZ=b;
	}
	
	public void setActualBearingVelocityXY(double b)
	{
		this.actualBearingVelocityXY=b;
	}
	
	public void setActualBearingXZ(double b)
	{
		this.actualBearingXZ=b;
	}
	
	public void setActualBearingXY(double b)
	{
		this.actualBearingXY=b;
	}

	public double getDesiredBearingVelocityXZ()
	{
		return this.desiredBearingVelocityXZ;
	}
	
	public double getDesiredBearingVelocityXY()
	{
		return this.desiredBearingVelocityXY;
	}	
	
	public double getActualBearingVelocityXZ()
	{
		return this.actualBearingVelocityXZ;
	}
	
	public double getActualBearingVelocityXY()
	{
		return this.actualBearingVelocityXY;
	}
	
	public double getActualBearingXZ()
	{
		return this.actualBearingXZ;
	}
	
	public double getActualBearingXY()
	{
		return this.actualBearingXY;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getLength()
	{
		return this.length;
	}

	public String getType()
	{
		return this.type;
	}
}
