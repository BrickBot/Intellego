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

package util;

import java.io.*;
import java.util.*;

/**
* Creates a log file and provides methods to add messages to it 
*
* @author Graham Ritchie
*/
public class IntellegoLog
{
	private File log;		// the log file
	private FileWriter fw;	// the filewriter
	
	/**
	* Creates the log file and writes a header message
	*/
	public IntellegoLog()
	{
		// create the log file
		log=new File("logs/Intellego.log");
		
		// get the current time
		Date date=new Date();
        String temp=date.toString();
        String time=temp.substring(0,19);
		
		// try to write a header to the file
		try
		{
			log.createNewFile();
			fw=new FileWriter(log);
			fw.write("========================\n  Intellego Log File\n========================\n\n");
			fw.write("System started on: "+time+"\n\nMessages:\n\n");
			fw.flush();
		}
		catch (Exception e)
		{
			System.out.println("IntellegoLog.init(): Failed to create log file: "+e);
		}
	}
	
	/**
	* Adds a message string to the log file
	*
	* @param message the message to be added
	*/
	public synchronized void addMessage(String message)
	{
		// try to write the message to the file
		try
		{
			fw.write(message+"\n");
			fw.flush();
		}
		catch (Exception e)
		{
			System.out.println("IntellegoLog.addMessage(): Failed to add log message: "+e);
		}
	}
}
