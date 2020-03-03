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
import simworldobjects.*;

import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
* Provides a dialog box to get intial robot parameters from the user
*
* @author Graham Ritchie
*/ 
public class InitRobotDialog extends JDialog implements ActionListener
{
		private JTextField xField, zField, bField;
        private JLabel xLabel, zLabel, bLabel;
        private JButton OK, cancel;
		private SimWorld world;
		private Controller controller;
		private SimDisplay display;

        /**
        * Displays a dialog box to get initial robot parameters, and creates and
		* creates a new SimRCX wth these values.
		*
		* @param w the SimWorld for the thr new robot to exist in
		* @param c the controller of the robot
		* @param d the simdisplay to display this robot in
        */
        public void createRobot(SimWorld w, Controller c, SimDisplay d)
        {
                setTitle("Initialise Robot:");
                setSize(200,135);
                setLocation(400,400);
				setVisible(true);
				
				world=w;
				controller=c;
				display=d;
				
                Container mainPanel=getContentPane();
                mainPanel.setLayout(new BorderLayout(1,1));

                Container bottomPanel=new Container();
                bottomPanel.setLayout(new BorderLayout(1,1));

                Container topPanel=new Container();
                topPanel.setLayout(new BorderLayout(1,1));

                Container Panel1=new Container();
                Panel1.setLayout(new BorderLayout(1,1));

                Container Panel2=new Container();
                Panel2.setLayout(new BorderLayout(1,1));

                Container Panel3=new Container();
                Panel3.setLayout(new BorderLayout(1,1));

                Container Panel4=new Container();
                Panel4.setLayout(new FlowLayout());

 				xField=new JTextField("500",4);
				zField=new JTextField("500",4);
				bField=new JTextField("45",4);
 
				xLabel=new JLabel("Enter X coordinate:");
                zLabel=new JLabel("Enter Z coordinate:");
                bLabel=new JLabel("Enter initial bearing");

                (OK=new JButton("OK")).addActionListener(this);
                (cancel=new JButton("Cancel")).addActionListener(this);

                Panel1.add("West",xLabel);
                Panel1.add("East",xField);
                Panel2.add("West",zLabel);
                Panel2.add("East",zField);
                Panel3.add("West",bLabel);
                Panel3.add("East",bField);
                Panel4.add("South",OK);
                Panel4.add("South",cancel);

                topPanel.add("North",Panel1);
                topPanel.add("South",Panel2);

                bottomPanel.add("North",Panel3);
                bottomPanel.add("South",Panel4);

                mainPanel.add("North",topPanel);
                mainPanel.add("South",bottomPanel);
				
				this.show();

        }

        /**
        *  Action event handler - creates a robot according to user input,
        *  having check that the input is valid.
        *
        *  @param e the action event
        */
        public void actionPerformed(ActionEvent e)
        {
			if (e.getSource()==OK)
			{
				int x=Integer.parseInt(xField.getText());
				int z=Integer.parseInt(zField.getText());
				int b=Integer.parseInt(bField.getText());
				
				long X=(long)x;
				long Z=(long)z;
				long B=(long)b;
				
				// create the robot
				SimRCX rcx=new SimRCX(world, controller, 30.0,40.0,60.0,"robot",X,0.0,Z,0.0,B);
				
				// add it to the controller
				controller.initController(rcx);
				
				// ... and add it to the world
				world.addObject((SimObject)rcx);
				
				display.repaint();
				
				dispose();
			}
			else
			{
				dispose();
			}
		}
}
