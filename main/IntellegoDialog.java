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
import java.awt.event.*;
import javax.swing.*;

/**
* Providea a general purpose dialog box to display messages from the
* system to the user.
*
* @author Graham Ritchie
*/
class IntellegoDialog extends JDialog implements ActionListener
{
	JLabel message;
	JButton OK;
	static int openFrameCount=5;
	static final int xOffset = 30, yOffset = 30;
	
	/**
	* Creates and displays the dialog box
	*
	* @param text the message to be displyed to the user
	*/
	public IntellegoDialog(String text)
	{
		openFrameCount++;
		
		setTitle("Intellego:");
        setSize(400,100);
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		
        Container c=getContentPane();
        c.setLayout(new BorderLayout(1,1));

        Container top=new Container();
        top.setLayout(new FlowLayout());

        Container bottom=new Container();
        bottom.setLayout(new FlowLayout());

        (OK=new JButton("OK")).addActionListener(this);
        message=new JLabel(text);

        top.add("Center",message);
        bottom.add("Center",OK);

        c.add("North",top);
        c.add("South",bottom);
		
		show();
	}
	
	/**
	* Disposes with the dialog box when the user clicks on OK
	*
	* @param e the action event
	*/
	public void actionPerformed(ActionEvent e)
	{
		dispose();
		openFrameCount--;
	}
}
