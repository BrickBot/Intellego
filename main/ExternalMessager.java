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
import java.net.*;

/**
* Provide a window which displays messages from external processes to the user, 
* within the main interface.
*
* @author Graham Ritchie
*/
public class ExternalMessager extends JInternalFrame
{
	private JEditorPane messagePane;
	static final int xOffset = 30, yOffset = 30;
	static int openFrameCount = 0;
	
	/**
	* Sets up the message display window
	*/
	public ExternalMessager()
	{
		super("External process messages: ",true,true,true,true);
		
		openFrameCount++;
		
		// create and add the editor pane
		messagePane=new JEditorPane();
		messagePane.setVisible(true);
		messagePane.setEditable(false);
		
		// put it in a scroll pane
		JScrollPane messageScrollPane = new JScrollPane(messagePane);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollPane.setPreferredSize(new Dimension(400,600));
        messageScrollPane.setMinimumSize(new Dimension(10, 10));
		
		// and add this to a main content pane
		JPanel contentPane = new JPanel();
		BoxLayout box = new BoxLayout(contentPane, BoxLayout.X_AXIS);
		contentPane.setLayout(box);
		contentPane.add(messageScrollPane);
		setContentPane(contentPane);
		
        // set the window size
        setSize(500,300);

        // and set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	}
	
	/**
	* Appends a message to the message pane of the main window
	*
	* @param text the test to be appended
	*/
	public void append(String text)
	{
		messagePane.setText(messagePane.getText()+text);
	} 
}
