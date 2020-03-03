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
* Interface class for both real and simulated robots.
* Defines all the input and output methods a controller
* can call.
*
* @author Graham Ritchie
*/
public interface AbstractRobot
{
	/******************************************
	   Methods for moving the robot - output
	******************************************/
	
	/**
	* Sets the robot moving forwards, this will continue until some other 
	* method is called to stop it.
	*/
	public abstract void forward();
	
	/**
	* Makes the robot move forwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public abstract void forward(int time);
	
	/**
	* Sets the robot moving backwards, this will continue until some other 
	* method is called to stop it.
	*/
	public abstract void backward();
	
	/**
	* Makes the robot move backwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public abstract void backward(int time);
	
	/**
	* Sets the robot spinning right, this will continue until some other 
	* method is called to stop it.
	*/
	public abstract void right();
	
	/**
	* Spins the robot right for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public abstract void right(int time);
	
	/**
	* Sets the robot spinning left, this will continue until some other 
	* method is called to stop it.
	*/
	public abstract void left();
	
	/**
	* Spins the robot left for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public abstract void left(int time);
	
	/**
	* Stops all motors immediately
	*/
	public abstract void stopMoving();
	
	
	/**
	* Makes the robot beep
	*/
	public abstract void beep();
	
	
	/*************************************************
	  Methods for reading the robot's sensors - input 
	**************************************************/
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public abstract int getSensor1();
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public abstract int getSensor2();
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public abstract int getSensor3();
} 
