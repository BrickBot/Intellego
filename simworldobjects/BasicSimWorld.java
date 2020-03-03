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

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
* Abstract class for SimWorlds, provides a base implementation of 
* the SimWorld interface which more complex worlds can extend. (e.g
* by adding lights, walls etc.)
*
* @see interfaces.SimWorld
*
* @author Graham Ritchie
*/
public abstract class BasicSimWorld implements SimWorld
{
	private int ticks;
	private int ambientLight;
	private LinkedList objectList;
	private long width, length, height;
	
	/**
	* Sets up the basic sim world
	*
	* @param x the largest x cooridnate in this world 
	* @param y the largest y cooridnate in this world 
	* @param z the largest z cooridnate in this world 
	*/
	public BasicSimWorld(long x, long y, long z)
	{	
		// initialise world size 
		width=x;
		height=y;
		length=z;
		
		// initialise tick counter
		ticks=0;
		
		// initialise object list
		objectList=new LinkedList();
		
		// set ambient light
		ambientLight=20;
	}
	
	/**
	* Performs one update loop
	*/
	public void tick()
	{
		updateObjects();
		ticks++;
	}
	
	/**
	* Returns the number of 'ticks' since this world was started
	*
	* @return the number of ticks as a long
	*/
	public long getTime()
	{
		return ticks;
	}
	
	/**
	* Returns the light level at the specified co-ordinate. 
	*
	* @return the brightness, this will always be an int between 0 and 100
	*/
	public int getBrightness(double x, double y, double z)
	{
		double totalBrightness=0;
		
		// check through the object list for SimLights
		for(int i=0;i<objectList.size();i++)
		{
			SimObject o=(SimObject)objectList.get(i);
			
			if(o instanceof SimLight)
			{
				SimLight light=(SimLight)o;
				
				int X=Math.abs((int)(light.getXCoord()-x));
				int Z=Math.abs((int)(light.getZCoord()-z));
				
				// work out distance from point to light with pythagoras
				double distance=Math.sqrt((X*X)+(Z*Z));
				
				// work out the reduction in light coefficient
				double coeff=(((double)light.getBrightness()-(distance/8))/(double)light.getBrightness());
				
				// establish the brightness of this light at the point
				double brightness=light.getBrightness()*coeff;
				
				// add this light's brightness to the total brightness level
				totalBrightness+=brightness;
			}
		}
		
		// make sure light never falls below ambient level
		if(totalBrightness < ambientLight)
		{
			totalBrightness=ambientLight;
		}
		
		// make sure light never goes above 100
		if(totalBrightness > 100)
		{
			totalBrightness=100;
		}
		
		// add some  random 'noise' to the light
		Random r=new Random(System.currentTimeMillis());
		totalBrightness=totalBrightness+(2*r.nextFloat());
				
		// return total brightness at this point as an int
		return (int) totalBrightness;
	}
	
	/**
	* Checks whether there is an obstacle in the specified co-ordinate
	*
	* @param x the x coordinate
	* @param y the y coordinate
	* @param z the z coordinate
	*
	* @return true or false accordingly
	*/
	public boolean hasObstacle(double x, double y, double z)
	{
		// examine each SimObject in turn
		for (int i=0;i<objectList.size();i++)
		{
			SimObject o=(SimObject)objectList.get(i);
			
			// create a shape the same size and bearing as o
			Shape s=createShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength(),o.getActualBearingXZ());
			
			// if o's shape contains the coords return true (ignoring sensors)
			if(s.contains(x,z) && !(o instanceof SimSensor))
			{
				// has obstacle
				return true;
			}
		}
		// if we've got here then there is no obstacle, so return false
		return false;
	}
	
	/**
	* Rotates a Shape around a certain point a certain angle 
	*
	* @param shape the shape to be rotated
	* @param angle the angle by which the shape is to be rotated
	* @param X the x co-ordinate about which to rotate
	* @param Z the z co-ordinate about which to rotate
	*
	* @return the rotated shape, as a new Shape
	*/
	private Shape rotateShape(Shape shape, double angle, double X, double Z)
	{
		// convert the angle to radians
		double theta=Math.toRadians(angle);
		
		// create a new affine transform rotator
		AffineTransform  atx = AffineTransform.getRotateInstance(theta,X,Z); 
    	
		// create a rotated version of the shape
    	shape = atx.createTransformedShape(shape);
		
		// return the shape
		return shape;
	}
	
	/**
	* Creates a rectangle with the given properties
	*
	* @param x the x coordinate of the rectangle 
	* @param z the z coordinate of the rectangle 
	* @param width the width of the rectangle 
	* @param width the length of the rectangle 
	* @param angle the bearing of the rectangle 
	*
	* @return the rectangle
	*/
	private Shape createShape(double x, double z, double width, double length, double angle)
	{
		// establish top left corner of object
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		// create shape
		Shape s=new Rectangle2D.Double(X,Z,width,length);
		
		// rotate it to the correct bearing
		s=rotateShape(s,angle,x,z);
		
		return s;
	}
	
	/**
	* Checks if SimObject o is colliding with SimObject p.
	*
	* @param o the first SimObject
	* @param p the second SimObject
	*
	* @return true or false accordingly
	*/
	public boolean colliding(SimObject o, SimObject p)
	{
		// create a rectangle the same size and bearing as o
		Shape shapeO=createShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength(),o.getActualBearingXZ());
		
		// create a rectangle the same size and bearing as p
		Shape shapeP=createShape(p.getXCoord(),p.getZCoord(),p.getWidth(),p.getLength(),p.getActualBearingXZ());
		
		// 'cast' shapeP to a Rectangle2D so intersects() will work
		Rectangle2D rectP=shapeP.getBounds2D();
		
		// check if o's shape intersects with p's shape
		if (shapeO.intersects(rectP))
		{
			// objects are colliding
			return true;
		}
		else
		{
			// objects aren't colliding
			return false;
		}
	}
	
	/**
	* Adds an object to this SimWorld
	*
	* @param o the SimObject to be added
	*/
	public void addObject(SimObject s)
	{
		// add this object to the list
		objectList.add(s);
	}
	
	/**
	* Returns this SimWorld's object list
	*
	* @return the object list as a LinkedList
	*/
	public LinkedList getObjectList()
	{
		return objectList;
	}
	
	/**
	* Updates all the SimObjects in this world by one step
	*/
	public void updateObjects()
	{
		// flag to determine whether any object has collided with any other
		boolean collided;
		
		for (int i=0;i<objectList.size();i++)
		{
			// examine each object in turn
			SimObject o=(SimObject)objectList.get(i);
			
			// reset collided flag
			collided=false;
			
			// check for collisions:
			
			// ignore sensors
			if(!(o instanceof SimSensor))
			{
				// see if it is colliding with any other object in the world
				for (int j=0;j<objectList.size();j++)
				{
					// examine each object in turn
					SimObject p=(SimObject)objectList.get(j);
					
					// ignore sensors
					if(!(p instanceof SimSensor))
					{
						// ignore comparisons with self (no other object should ever have the same
						// x and z coords (if this works!)
						if(!(o.getXCoord()==p.getXCoord() && o.getZCoord()==p.getZCoord()))
						{
							if(colliding(o,p))
							{	
								//System.out.println("collision: "+p.getType()+" with "+o.getType());
								collided=true;
							}
						}
					}
				}
			}
			
			if(!collided)
			{
				// determine what the object wants to do, and do it
				
				if(o.getDesiredVelocity()>0)
				{
					moveForward(o);
				}
				else if(o.getDesiredVelocity()<0)
				{
					moveBackward(o);
				}
				
				if(o.getDesiredBearingVelocityXZ()>0)
				{
					moveRight(o);
				}
				else if(o.getDesiredBearingVelocityXZ()<0)
				{
					moveLeft(o);
				}
			}
			else // objects are currently collided so ...
			{
				// do the opposite to what it wants to do
				
				if(o.getActualVelocity()>0 && o.getActualVelocity()!=o.getDesiredVelocity())
				{
					moveBackward(o);
				}
				else if(o.getActualVelocity()<0 && o.getActualVelocity()!=o.getDesiredVelocity())
				{
					moveForward(o);
				}
				
				if(o.getActualBearingVelocityXZ()>0 && o.getActualBearingVelocityXZ()!=o.getDesiredBearingVelocityXZ())
				{
					moveLeft(o);
				}
				else if(o.getActualBearingVelocityXZ()<0 && o.getActualBearingVelocityXZ()!=o.getDesiredBearingVelocityXZ())
				{
					moveRight(o);
				}
			}
		}
	}	
	
	/**
	* Moves the object forward one step according to its current bearing
	*
	* @param o the SimObject to be moved
	*/
	private void moveForward(SimObject o)
	{
		o.setActualVelocity(1);
		o.setXCoord(o.getXCoord() + Math.sin(Math.toRadians(o.getActualBearingXZ())));
		o.setZCoord(o.getZCoord() - Math.cos(Math.toRadians(o.getActualBearingXZ())));
	}
	
	/**
	* Moves the object backward one step according to its current bearing
	*
	* @param o the SimObject to be moved
	*/
	private void moveBackward(SimObject o)
	{
		o.setActualVelocity(-1);
		o.setXCoord(o.getXCoord() - Math.sin( Math.toRadians(o.getActualBearingXZ())));
		o.setZCoord(o.getZCoord() + Math.cos( Math.toRadians(o.getActualBearingXZ())));
	}
	
	/**
	* Turns the object one step right (clockwise)
	*
	* @param o the SimObject to be moved
	*/
	private void moveRight(SimObject o)
	{
		o.setActualBearingVelocityXZ(1);
		o.setActualBearingXZ((o.getActualBearingXZ()+1));
	}
	
	/**
	* Turns the object one step left (anticlockwise)
	*
	* @param o the SimObject to be moved
	*/
	private void moveLeft(SimObject o)
	{
		o.setActualBearingVelocityXZ(-1);
		o.setActualBearingXZ((o.getActualBearingXZ()-1));
	}
}
