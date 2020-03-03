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
* Models a simple world with a 'maze' of walls
*
* @author Graham Ritchie
*/
public class MazeWorld extends BasicSimWorld
{
	/**
	* Creates the MazeWorld
	*/
	public MazeWorld()
	{
		// initialise the SimWorld size
		super(1000,1000,1000);
		
		// add maze walls
		
		// top
		SimWall wall1=new SimWall(500.0,0.0,50.0,0.0,800.0,10.0);
		addObject(wall1);
		
		// bottom
		SimWall wall2=new SimWall(500.0,0.0,650.0,0.0,800.0,10.0);
		addObject(wall2);
		
		// right
		SimWall wall3=new SimWall(900.0,0.0,350.0,90.0,600.0,10.0);
		addObject(wall3);
		
		//left
		SimWall wall4=new SimWall(100.0,0.0,350.0,90.0,600.0,10.0);
		addObject(wall4);
		
		// inner bottom
		SimWall wall5=new SimWall(430.0,0.0,520.0,0.0,650.0,10.0);
		addObject(wall5);
		
		// inner top
		SimWall wall6=new SimWall(500.0,0.0,150.0,0.0,500.0,10.0);
		addObject(wall6);
		
		// inner left
		SimWall wall7=new SimWall(250.0,0.0,335.0,90.0,360.0,10.0);
		addObject(wall7);
		
		// small top right
		SimWall wall8=new SimWall(750.0,0.0,200.0,90.0,110.0,10.0);
		addObject(wall8);
		
		// small bottom right
		SimWall wall9=new SimWall(750.0,0.0,470.0,90.0,110.0,10.0);
		addObject(wall9);
		
		// middle
		SimWall wall10=new SimWall(470.0,0.0,320.0,90.0,200.0,10.0);
		addObject(wall10);
		
	}
}
