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

package main;

import intellego.Intellego;
import util.*;
import interfaces.*;
import real.*;

import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
* The main GUI.
*
* @author Graham Ritchie
*/
public class MainInterface extends JFrame
{
	private static JDesktopPane mainPane; // the main desktop pane
	
	/**
	* Sets up the JDesktopPane
	*/
	public MainInterface()
	{
		super("Intellego");

        // Make the main window indented 50 pixels from each edge of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset*2, screenSize.height-inset*2);
		
        // Quit the whole program when the main window closes.
        addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e) 
			{
                Intellego.addToLog("MainInterface.init(): System quitting");
				System.exit(0);
            }
        });
		
		// set up the main pane
		mainPane=new JDesktopPane();
		
        setContentPane(mainPane);
        setJMenuBar(createMenuBar());
	}
	
	/**
	* Creates a new code editor window
	*/
	public static void createCodeEditorFrame() 
	{
        CodeEditor frame = new CodeEditor();
		frame.setVisible(true);
        mainPane.add(frame);
        try 
		{
            frame.setSelected(true);
        }
		catch (Exception e) 
		{
			Intellego.addToLog("MainInterface.createFrame(): Failed to create internal code editor frame properly: "+e);
		}
    }
	
	/**
	* Creates a new simulator window
	*/
	public static void createSimulatorFrame() 
	{
        SimUI frame = new SimUI();
		frame.setVisible(true);
        mainPane.add(frame);
        try 
		{
            frame.setSelected(true);
        }
		catch (Exception e) 
		{
			Intellego.addToLog("MainInterface.createFrame(): Failed to create internal simulator frame properly: "+e);
		}
    }
	
	/**
	* Creates a new simulator window with the specified controller preloaded
	*
	* @param className the name of the controller class to be preloaded
	*/
	public static void createSimulatorFrame(String className) 
	{
        SimUI frame = new SimUI(className);
		frame.setVisible(true);
        mainPane.add(frame);
        try 
		{
            frame.setSelected(true);
        }
		catch (Exception e) 
		{
			Intellego.addToLog("MainInterface.createFrame(): Failed to create internal simulator frame properly: "+e);
		}
    }
	
	/**
	* Creates a new window to display messages from external processes
	*
	* @return the ExternalMessager frame
	*/
	public static ExternalMessager createExternalMessagerFrame() 
	{
        ExternalMessager frame = new ExternalMessager();
		frame.setVisible(true);
        mainPane.add(frame);
        try 
		{
            frame.setSelected(true);
        }
		catch (Exception e) 
		{
			Intellego.addToLog("MainInterface.createFrame(): Failed to create internal frame properly: "+e);
		}
		return frame;
    }
	
	/**
	* Creates the menu bar for the main desktop pane
	*
	* @return the menu bar
	*/
	private JMenuBar createMenuBar() 
	{
        JMenuBar menuBar = new JMenuBar();

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem codeEditor = new JMenuItem("Code Editor");
		JMenuItem simulator = new JMenuItem("Simulator");
		
        codeEditor.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                createCodeEditorFrame();
            }
        });
		
		simulator.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                createSimulatorFrame();
            }
        });
		
        toolsMenu.add(codeEditor);
		toolsMenu.add(simulator);
		
        menuBar.add(toolsMenu);

        return menuBar;
    }
	
	/**
	* Displays messages to the user in a dialog box
	*
	* @param message the message to be displayed to the user
	*/
	public static void displayMessage(String message)
	{
		// pop up a dialog box with the message
		IntellegoDialog dialog=new IntellegoDialog(message);
	}
}
