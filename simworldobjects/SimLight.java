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
* Models a simple light source
*
* @author Graham Ritchie
*/
public class SimLight extends BasicSimObject
{
	// central brightness of this light
	private int brightness;
	
	/**
	* Sets up this light
	*
	* @param brightness the intensity of the light - must be between 1 and 100
	* @param x the light's x coordinate
	* @param y the light's y coordinate
	* @param z the light's z coordinate
	*/
	public SimLight(int brightness, double x, double y, double z)
	{
		// initialise the SimObject
		super(30.0,30.0,30.0,"light",x,y,z,0.0,0.0);
		
		this.brightness=brightness;
	}

	/**
	* Returns the brightness of this light, this will always be a value between 0 and 100
	*
	* @return the brightness
	*/
	public int getBrightness()
	{
		return brightness;
	}
}
