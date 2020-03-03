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

import java.io.*;
import josx.rcxcomm.*;
import josx.platform.rcx.*;

/**
* Runs onboard the RCX listening for controller commands from the PC. 
* Talks to the HybridRCX class.
*
* @author Graham Ritchie
*/
public class HybridOnBoard implements SensorConstants, HybridCommandConstants
{
	public static void main(String args[])
	{
		// set robot up
		int MOTOR_POWER=3;
		Motor.A.setPower(MOTOR_POWER);
		Motor.C.setPower(MOTOR_POWER);
		
		// currently only support light sensors
		Sensor.S1.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
		Sensor.S1.activate();
		
		Sensor.S2.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
		Sensor.S2.activate();
		
		Sensor.S3.setTypeAndMode (SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
		Sensor.S3.activate();
		
		try 
		{
      		// set up the data ports
			RCXPort port = new RCXPort();

      		InputStream is = port.getInputStream();
      		OutputStream os = port.getOutputStream();
      		DataInputStream dis = new DataInputStream(is);
      		DataOutputStream dos = new DataOutputStream(os);
			
			// just keep on listeneing for the PC command
      		while (true) 
			{
        		// get PC command
				int command = dis.readInt();
			
				if(command==HybridCommandConstants.MOVE_FORWARD)
				{
					// move forward
					Motor.A.forward();
					Motor.C.forward();
				}
				else if(command==HybridCommandConstants.MOVE_BACKWARD)
				{
					// move backward
					Motor.A.backward();
					Motor.C.backward();
				}
				else if(command==HybridCommandConstants.MOVE_RIGHT)
				{
					// move right
					Motor.A.stop();
					Motor.C.stop();
					Motor.A.forward();
					Motor.C.backward();
				}
				else if(command==HybridCommandConstants.MOVE_LEFT)
				{
					// move left
					Motor.A.stop();
					Motor.C.stop();
					Motor.A.backward();
					Motor.C.forward();
				}
				else if(command==HybridCommandConstants.STOP_MOVING)
				{
					// stop
					Motor.A.stop();
					Motor.C.stop();
				}
				else if(command==HybridCommandConstants.BEEP)
				{
					// beep
					Sound.beep();
				}	
				else if(command==HybridCommandConstants.GET_S1)
				{
					// get value of sensor 1 & write value back to the PC
					dos.writeInt(Sensor.S1.readValue());
        			dos.flush();
				}
				else if(command==HybridCommandConstants.GET_S2)
				{
					// get value of sensor 2 & write value back to the PC
					dos.writeInt(Sensor.S2.readValue());
        			dos.flush();
				}
				else if(command==HybridCommandConstants.GET_S3)
				{
					// get value of sensor 3 & write value back to the PC
					dos.writeInt(Sensor.S3.readValue());
        			dos.flush();
				}
			}
    	}
		// if an exception occurs just ignore it - there's not much we can do    
    	catch (Exception e){}
	}
}
