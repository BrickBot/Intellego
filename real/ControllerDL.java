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

package real;

import intellego.Intellego;
import interfaces.*;
import main.*;

import java.io.*;
import java.lang.*;

/**
* This class provides the functionality necessary to download an Intellego
* Controller to the real RCX robot.
*
* @author Graham Ritchie
*/
public class ControllerDL
{
	// the current file
	private File currentFile;
	
	//the current file's local name
	private String currentFileName;
	
	// the current file's directory
	private File currentDir;
	
	/**
	* Constructor: attempts to download the controller file passed to it.
	*
	* @param sourceFile the Java source file of the controller to be downloaded
	* @param dir the directory where this file resides.
	*/
	public ControllerDL(File sourceFile, File dir)
	{
		currentFile=sourceFile;
		currentFileName=currentFile.getName();
		currentDir=dir;
		
		currentFile=createFile(currentFileName.substring(0,currentFileName.length() - 5));
		
		compileFile();
		downloadFile();
	}
	
	/**
	* Creates a Java file with a main method to allow the controller to be run
	* in the real RCX. It is this file which is then downloaded to the RCX, and 
	* it 'pulls' the Controller file with it.
	*/
	public File createFile(String className)
	{
		// create the file
		File file=new File("real","Real"+className+".java");
		
		// try to write the code to the file
		try
		{
			FileWriter fw=new FileWriter(file);
			
			fw.write(
			"\n// This file was generated by Intellego. Do not modify\n\n"+
			"class Real"+className+"\n{\n\tpublic static void main(String args[])\n"+
			"\t{\n\t\t"+className+" a=new "+className+"();"+
			"\n\t\ta.initController(new real.RealRCX(a));\n\t\ta.run();\n\t}\n}");
			
			fw.close();
			
			Intellego.addToLog("ControllerDL.createFile(): Created file: "+file.getName());
		}
		
		catch(Exception e)
		{
			MainInterface.displayMessage("Error: cannot download controller");
			Intellego.addToLog("ControllerDL.createFile() failedto create file: "+e);
		}
		
		return file;
	}
	
	/**
	* Compiles the current file
	*/
	private void compileFile()
	{
		if(currentFile!=null)
		{
			externalCommand("lejosc -target 1.1 "+currentFile.toString());
		}
		else
		{
			MainInterface.displayMessage("ControllerDL.compileFile(): cannot compile null file");
		}
	}
	
	/**
	* Links and downloads the current file
	*/
	private void downloadFile()
	{
		if(currentFile!=null)
		{
			//download
			
			// get rid of .class extension
			String className=currentFile.getName();
			className=className.substring(0,className.length() - 5);
			
			// actually download
			externalCommand(("lejos "+className)); 

		}
		else
		{
			MainInterface.displayMessage("ControllerDL.downloadFile(): cannot download null file");
		}
	}
	
	/**
	* Processes all external calls, i.e calls to lejos & lejosc
	*/
	private void externalCommand(String cmd)
	{
		int len;
        byte buffer[] = new byte[1000];
		
		Intellego.addToLog("ControllerDL.externalCommand(): External Command attempted: "+cmd);
		
		try
		{
			Process external=Runtime.getRuntime().exec(cmd);
            InputStream ees = external.getErrorStream();
			
            try 
			{
            	// display external messages
				ExternalMessager output=MainInterface.createExternalMessagerFrame();
				
				while ((len = ees.read(buffer)) != -1)
				{
                	String eo = new String (buffer, 0, len);
                    output.append(eo);            
				}
                external.waitFor();
             }
			 catch (Exception e) 
			 {
             	MainInterface.displayMessage("Error attempting external command");
				Intellego.addToLog("ControllerDL.externalCommand(): error: "+e.getMessage());
             }
		}
		catch (Exception e) 
		{
        	MainInterface.displayMessage("Error attempting external command");
			Intellego.addToLog("ControllerDL.externalCommand(): error: "+e.getMessage());
       	}
	}
}