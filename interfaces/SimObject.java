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

package interfaces;

/**
* Interface class for all objects in a SimWorld
*
* @author Graham Ritchie
*/
public interface SimObject
{
	/**
	* Sets the desired velocity of this object - the SimWorld will then set its
	* actual velocity accordingly (depending on wheher the object is colliding 
	* with another object etc.) Positive values mean the objet wants to move 
	* forward, and negative values mean the object wants to move backward. 
	* A velocity of 0 means the object wants to stop.
	*
	* @param v the desired velocity
	*/
	public void setDesiredVelocity(double v);
	
	/**
	* Sets the actual velocity of this object.
	*
	* @param v the actual velocity
	*/
	public void setActualVelocity(double v);
	
	/**
	* Sets this objects X coordinate
	*
	* @param x the X coordinate
	*/
	public void setXCoord(double x);
	
	/**
	* Sets this objects Y coordinate
	*
	* @param y the Y coordinate
	*/
	public void setYCoord(double y);
	
	/**
	* Sets this objects Z coordinate
	*
	* @param z the Z coordinate
	*/
	public void setZCoord(double z);
	
	/**
	* Returns this objects desired velocity. This may not be equal to its 
	* actual velocity.
	*
	* @return the desired velocity as a double
	*/
	public double getDesiredVelocity();
	
	/**
	* Returns this objects actual velocity. 
	*
	* @return the actual velocity as a double
	*/
	public double getActualVelocity();
	
	/**
	* Returns this objects X coordinate
	*
	* @return the X coordinate as a double
	*/
	public double getXCoord();
	
	/**
	* Returns this objects Y coordinate
	*
	* @return the Y coordinate as a double
	*/
	public double getYCoord();
	
	/**
	* Returns this objects Z coordinate
	*
	* @return the Z coordinate as a double
	*/
	public double getZCoord();
	
	/**
	* Sets the desired 'bearing velocity' of this object in the XZ plane - 
	* a bearing velocity is the rate at which the object wants to turn. 
	* Positive values mean the object wants to turn clockwise, negative values 
	* mean the object wants to turn antoclockwise and a deisred bearing velocity 
	* of 0 means the object does not want to turn at all.
	*
	* @param v the desired bearing velocity in the XZ plane
	*/
	public void setDesiredBearingVelocityXZ(double v);
	
	/**
	* Sets the desired 'bearing velocity' of this object in the XY plane.
	*
	* @param v the desired bearing velocity in the XY plane
	*/
	public void setDesiredBearingVelocityXY(double v);
	
	/**
	* Sets the actual 'bearing velocity' of this object in the XZ plane.
	*
	* @param b the actual bearing velocity in the XZ plane
	*/
	public void setActualBearingVelocityXZ(double b);
	
	/**
	* Sets the actual 'bearing velocity' of this object in the XY plane.
	*
	* @param b the actual bearing velocity in the XY plane
	*/
	public void setActualBearingVelocityXY(double b);
	
	/**
	* Sets the actual bearing of this object in the XZ plane
	*
	* @param b the bearing
	*/
	public void setActualBearingXZ(double b);
	
	/**
	* Sets the actual bearing of this object in the XY plane
	*
	* @param b the bearing
	*/
	public void setActualBearingXY(double b);
	
	/**
	* Returns this objects desired bearing velocity in the XZ plane
	*
	* @return the desired bearing velocity as a double
	*/
	public double getDesiredBearingVelocityXZ();
	
	/**
	* Returns this objects desired bearing velocity in the XY plane
	*
	* @return the desired bearing velocity as a double
	*/
	public double getDesiredBearingVelocityXY();
	
	/**
	* Returns this objects actual bearing velocity in the XZ plane
	*
	* @return the actual bearing velocity as a double
	*/
	public double getActualBearingVelocityXZ();
	
	/**
	* Returns this objects actual bearing velocity in the XY plane
	*
	* @return the actual bearing velocity as a double
	*/
	public double getActualBearingVelocityXY();
	
	/**
	* Returns this objects actual bearing in the XZ plane
	*
	* @return the actual bearing as a double
	*/
	public double getActualBearingXZ();
	
	/**
	* Returns this objects actual bearing in the XY plane
	*
	* @return the actual bearing as a double
	*/
	public double getActualBearingXY();
	
	/**
	* Returns this object's height
	*
	* @return the height as a double
	*/
	public double getHeight();
	
	/**
	* Returns this object's width
	*
	* @return the width as a double
	*/
	public double getWidth();
	
	/**
	* Returns this object's length
	*
	* @return the length as a double
	*/
	public double getLength();
	
	/**
	* Returns a string describing this object's type
	*
	* @return the type as a string
	*/
	public String getType();
}
