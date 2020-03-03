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

package main;

import interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.awt.geom.*;

/**
* Provides a simple display for any SimWorld
*
* @author Graham Ritchie
*/
class SimpleDisplay extends SimDisplay
{
	private SimWorld world;
	private Graphics2D g2;
	private BasicStroke stroke;
	private Color color;
	
	/**
	* Sets up this display
	*
	* @param s the SimWorld to be displayed
	*/
	public SimpleDisplay(SimWorld s)
	{
		// set world
		world=s;
		this.repaint();
		stroke=new BasicStroke(1.0f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL);
	}
	
	/**
	* Main repaint method
	*/
	public void paintComponent(Graphics g)
	{
		// paint background
		super.paintComponent(g);
		
		this.setBackground(Color.white);
				
		// cast g to a Graphics2D instance
		g2=(Graphics2D)g;
		
		// get the world's object list
		LinkedList list=world.getObjectList();
		
		// draw a shape for each object
		for (int i=0;i<list.size();i++)
		{
			// get each object in the list in turn
			SimObject o=(SimObject)list.get(i);
			
			// create a shape for this object
			Shape s=getShape(o);
			
			// rotate shape to correct bearing
			s=rotateShape(s,o.getActualBearingXZ(),o.getXCoord(),o.getZCoord());
			
			// draw shape on the screen
			
			g2.setPaint(Color.black);
			
			Shape outline=stroke.createStrokedShape(s);
			
			g2.draw(outline);
			
			g2.setPaint(color);
			
			g2.fill(s);
		}
	}
	
	/**
	* Checks if there is an associated shape for a SimObject. If so it is 
	* returned. If not then a standard shape of the SimObject's size is returned.
	*
	* @param o the SimObject
	* @return the appropriate java.awt.Shape
	*/
	public Shape getShape(SimObject o)
	{
		// create a new Shape
		Shape s;
		
		// check the SimObject's type
		if(o.getType().equalsIgnoreCase("robot"))
		{
			// SimObject is a robot
			s=createRobotShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength());
		}
		else if(o.getType().equalsIgnoreCase("light"))
		{
			// SimObject is a light
			s=createLightShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength());
		}
		else if(o.getType().equalsIgnoreCase("sensor"))
		{
			// SimObject is a sensor
			s=createSensorShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength());
		}
		else if (o.getType().equalsIgnoreCase("wall"))
		{
			//SimObject is a wall
			s=createWallShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength());
		}
		else
		{
			// unknown SimObject, so create standard shape
			s=createStandardShape(o.getXCoord(),o.getZCoord(),o.getWidth(),o.getLength());
		}
		
		// return the shape
		return s;
	}
	
	/**
	* Rotates a java.awt.Shape around a certain point a certain angle 
	*
	* @param shape the shape to be rotated
	* @param angle the angle by which the shape is to be rotated
	* @param X the x co-ordinate about which to rotate
	* @param Z the z co-ordinate about which to rotate
	* @return the rotated shape, as a new java.awt.Shape
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
	* Creates a robot shape
	*
	* @param x the x coordinate of the robot
	* @param z the z coordinate of the robot
	* @param width the width of the robot
	* @param length the length of the robot
	*
	* @return the robot shape
	*/
	private Shape createRobotShape(double x, double z, double width, double length)
	{
		// size of the 'wheels'
		double a=5.0;
		double b=15.0;
		
		// establish top left corner of robot
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		// make up the shape
		Rectangle2D.Double body=new Rectangle2D.Double(X,Z,width,length);
		
		Rectangle2D.Double wheel1=new Rectangle2D.Double(X-a-1,Z+5,a,b);
		Rectangle2D.Double wheel2=new Rectangle2D.Double(X-a-1,Z+40,a,b);
		Rectangle2D.Double wheel3=new Rectangle2D.Double(X+width+1,Z+5,a,b);
		Rectangle2D.Double wheel4=new Rectangle2D.Double(X+width+1,Z+40,a,b);
		
		Area shape=new Area(body);
		shape.add(new Area(wheel1));
		shape.add(new Area(wheel2));
		shape.add(new Area(wheel3));
		shape.add(new Area(wheel4));
		
		color=Color.lightGray;
		
		// return the complete shape
		return (Shape) shape;
	}
	
	/**
	* Creates a light shape of the appropriate dimesnion
	*/
	private Shape createLightShape(double x, double z, double width, double length)
	{
		// establish top left corner of object
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		color=Color.yellow;
		
		return new Ellipse2D.Double(X,Z,width,length);
	}
	
	/**
	* Creates a sensor shape of the appropriate dimension
	*/
	private Shape createSensorShape(double x, double z, double width, double length)
	{
		// establish top left corner of object
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		color=Color.cyan;
		color=color.darker();
		
		return new Rectangle2D.Double(X,Z,width,length);
	}
	
	/**
	* Creates a wall shape of the appropriate dimension
	*/
	private Shape createWallShape(double x, double z, double width, double length)
	{
		// establish top left corner of object
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		color=Color.lightGray;
		color=color.darker();
		
		return new Rectangle2D.Double(X,Z,width,length);
	}
	
	/**
	* Creates a standard shape, currently a rectangle
	*/
	private Shape createStandardShape(double x, double z, double width, double length)
	{
		// establish top left corner of object
		double X=(x-(width/2));
		double Z=(z-(length/2));
		
		color=Color.black;
		
		return new Rectangle2D.Double(X,Z,width,length);
	}
}
