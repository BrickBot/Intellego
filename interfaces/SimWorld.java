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

import java.util.*;

/**
* Interface class for all SimWorlds.
*
* @author Graham Ritchie
*/
public interface SimWorld
{
	/**
	* Performs one update loop
	*/
	public void tick();
	
	/**
	* Returns the number of 'ticks' since this world was started
	*
	* @return the number of ticks as a long
	*/
	public long getTime();

	/**
	* Returns the light level at the specified co-ordinate. 
	*
	* @return the brightness, this will always be an int between 0 and 100
	*/
	public int getBrightness(double x, double y, double z);

	/**
	* Checks whether there is an obstacle in the specified co-ordinate
	*
	* @return true or false accordingly
	*/
	public boolean hasObstacle(double x, double y, double z);

	/**
	* Returns this SimWorld's object list
	*
	* @return the object list as a LinkedList
	*/
	public LinkedList getObjectList();
	
	/**
	* Adds an object to this SimWorld
	*
	* @param o the SimObject to be added
	*/
	public void addObject(SimObject o);
}
