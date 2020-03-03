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

import simworldobjects.*;

/**
* Models a simple world with a single light source enclosed in a squre 'pen'
* of walls.
*
* @author Graham Ritchie
*/
public class LightWorld extends BasicSimWorld
{
	/**
	* Creates the LightWorld
	*/
	public LightWorld()
	{
		// initialise the SimWorld size
		super(1000,1000,1000);
		
		// add a light
		SimLight light=new SimLight(100,300.0,0.0,300.0);
		addObject(light);
		
		// add a few containing walls
		SimWall wall1=new SimWall(450.0,0.0,60.0,0.0,600.0,10.0);
		addObject(wall1);
		
		SimWall wall2=new SimWall(450.0,0.0,660.0,0.0,600.0,10.0);
		addObject(wall2);
		
		SimWall wall3=new SimWall(140.0,0.0,360.0,90.0,600.0,10.0);
		addObject(wall3);
		
		SimWall wall4=new SimWall(760.0,0.0,360.0,90.0,600.0,10.0);
		addObject(wall4);
	}
}
