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

package hybrid;

import interfaces.*;

import java.io.*;
import josx.rcxcomm.*;
import josx.platform.rcx.*;

/**
* Acts as an interface between the PC and an RCX running the HybridOnBoard class.
* Enables controllers to be run on the PC, and communicate directly with the RCX
* over the IR link.
*
* @author Graham Ritchie
*/
public class HybridRCX extends Thread implements AbstractRobot, HybridCommandConstants
{
	RCXPort port;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	
	/**
	* Constructor: sets up the data ports
	*/
	public HybridRCX(Controller controller)
	{
		try
		{
			port=new RCXPort();
			is = port.getInputStream();
      		os = port.getOutputStream();
      		dis = new DataInputStream(is);
      		dos = new DataOutputStream(os);
		}
		catch(Exception e){}
	}
	
	public void run(){}
	
	/*******************************************************************
	                Methods required by AbstractRobot
	*******************************************************************/
	
	/**
	* Sets the robot moving forwards, this will continue until some other 
	* method is called to stop it.
	*/
	public void forward()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_FORWARD);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Makes the robot move forwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void forward(int time)
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_FORWARD);
			dos.flush();
			sleep(time);
			dos.writeInt(HybridCommandConstants.STOP_MOVING);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Sets the robot moving backwards, this will continue until some other 
	* method is called to stop it.
	*/
	public void backward()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_BACKWARD);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Makes the robot move backwards for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void backward(int time)
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_BACKWARD);
			dos.flush();
			try{sleep(time);}catch(Exception e){}
			dos.writeInt(HybridCommandConstants.STOP_MOVING);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Sets the robot spinning right, this will continue until some other 
	* method is called to stop it.
	*/
	public void right()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_RIGHT);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Spins the robot right for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void right(int time)
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_RIGHT);
			dos.flush();
			sleep(time);
			dos.writeInt(HybridCommandConstants.STOP_MOVING);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Sets the robot spinning left, this will continue until some other 
	* method is called to stop it.
	*/
	public void left()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_LEFT);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Spins the robot left for the given amount of time
	*
	* @param time the time in milliseconds
	*/
	public void left(int time)
	{
		try
		{
			dos.writeInt(HybridCommandConstants.MOVE_LEFT);
			dos.flush();
			sleep(time);
			dos.writeInt(HybridCommandConstants.STOP_MOVING);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Stops all motors immediately
	*/
	public void stopMoving()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.STOP_MOVING);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Makes the robot beep
	*/
	public void beep()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.BEEP);
			dos.flush();
		}
		catch(Exception e){}
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor1()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.GET_S1);
			dos.flush();
		
			int x=dis.readInt();
			
			return x;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor2()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.GET_S2);
			dos.flush();
		
			int x=dis.readInt();
			
			return x;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	/**
	* Get the current reading of this sensor
	*
	* @return the current value
	*/
	public int getSensor3()
	{
		try
		{
			dos.writeInt(HybridCommandConstants.GET_S3);
			dos.flush();
			
			int x=dis.readInt();
			
			return x;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
}
