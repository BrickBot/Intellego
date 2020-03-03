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

/**
* Models a simple wall object
*
* @author Graham Ritchie
*/
public class SimWall extends BasicSimObject
{
	/**
	* Sets up this wall
	*
	* @param x the wall's x coordinate
	* @param y the wall's y coordinate
	* @param z the wall's z coordinate
	* @param b the wall's bearing 
	* @param length the wall's length
	* @param width the wall's width
	*/
	public SimWall(double x, double y, double z, double b, double length, double width)
	{
		// initialise the SimObject
		super(0.0,length,width,"wall",x,y,z,0.0,b);
	}
}
