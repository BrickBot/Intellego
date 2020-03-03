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

import interfaces.Controller;
import interfaces.AbstractRobot;

import java.lang.*;
import java.util.*;

/**
* An 'inverse' version of the Q-learner - instead of being rewarded for seeking 
* light the robot gets rewarded for getting further away from it
*
* @author Graham Ritchie
*/
public class InverseQLR3 extends Controller
{
	/******************************************************************
				  Variables, parameters & data structures
	*******************************************************************/
	
	private AbstractRobot robot;	// this controller's robot
	
	private int STATES=4;			// no. of states
	private int ACTIONS=5;			// no. of actions
	private int LEEWAY=6;			// leeway for state decision
	private int REWARD_LEEWAY=1;	// leeway for reward
	private int SLEEP_TIME=500;
	int STOP_THRESHOLD=1000;
	
	// sensor type array
	private int[] sensors={Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT,Controller.SENSOR_TYPE_LIGHT};
	
	// states
	private int S1=0;
	private int S2=1;
	private int S3=2;
	private int S4=3;
	
	// actions
	private int A1=0;
	private int A2=1;
	private int A3=2;
	private int A4=3;
	private int A5=4;
	
	// variables
	private int state, newState, prevLight, newLight, reward, action;
	private boolean running;
	
	// the Q table
	private double table[][]=new double[STATES][ACTIONS]; 
	
	
	/******************************************************************
					  Methods required by Controller
	*******************************************************************/
	
	public void initController(AbstractRobot r)
	{
		robot=r;
		
		// initialise the Q table
		initTable();
	}
	
	public int[] getSensors()
	{
		return sensors;
	}
	
	public void run()
	{
		// set running to true
		running=true;
		
		// call main loop (will return when running=false)
		begin();
	}
	
	public void halt()
	{
		// set running to false, this will force run() to return, and therefore 
		// stop the controller's thread
		running=false;
		
		// also stop the robot
		robot.stopMoving();
	}
	
	public AbstractRobot getRobot()
	{
		return robot;
	}
	
	/******************************************************************
							  Other methods
	*******************************************************************/
	
	/**
	* Generates a random number between 0 and the limit
	*
	* @param limit
	* @return the random number as an int
	*/
	public int rand(int limit)
	{
		return (int) (Math.random()*(limit+1));
	} 
	
	/**
	* Initialises the Q-table entries to random numbers between 0 and 3
	*/
	public void initTable()
	{
		for (int i=0;i<STATES;i++)
		{
			for (int j=0;j<ACTIONS;j++)
			{
				int x=rand(3);
				table[i][j]=x;
			}			
		}
	}
	
	/**
	* Updates the utility table according to the Q learning equation
	*/
	public void updateTable()
	{
		// main q learning update equation
		table[state][action]=table[state][action]+reward;
	}
	
	/**
	* The main Q(s,a) function
	*
	* @return the current best action to perform (as an int)
	*/
	public int Q(int STATE)
	{
		int highest=0;
		
		for (int i=0;i<ACTIONS;i++)
		{
			if(table[STATE][i]>table[STATE][highest])
			{
				highest=i;
			}
		}
		
		return highest;
	}
	
	/**
	* Sets the system going
	*/
	public void begin()
	{
		int exploreRate=9;
		
		// establish initial state of robot
		
		if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){newState=S1;}
		if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){newState=S2;}
		if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){newState=S3;}
		if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){newState=S4;}
		
		// get current light level
		newLight=robot.getSensor2();
		
		// the tolerance of the robot to light, used to help move 
		// the robot towards light
		// int tolerance=0;
		
		// main loop
		while (running)
		{

			// save current light level
			prevLight=newLight;
			
			// establish current state of robot
			state=newState;
			
			// choose action randomly 10% of thetime
			//if(rand(9)==0){action=rand(ACTIONS-1);}
			if(rand(exploreRate)==0)
			{
				action=rand(ACTIONS-1);
				// System.out.println("Exploring... rate: "+exploreRate);
				exploreRate++;
			}
			// and according to Q table the rest
			else{action=Q(state);}
			
			// perform chosen action
			if(action==A1){robot.forward();}
			if(action==A2){robot.backward();}
			if(action==A3){robot.right();}
			if(action==A4){robot.left();}
			if(action==A5){robot.stopMoving();}
			
			// sleep for a wee bit to allow the action to have some effect
			try{sleep(SLEEP_TIME);}catch(Exception e){}
			
			// stop robot to begin loop again
			robot.stopMoving();
			
			// determine new light level
			newLight=robot.getSensor2();
			
			// and reward accordingly
			if (newLight > prevLight+REWARD_LEEWAY){reward=-1;robot.beep();}
			else if (newLight < prevLight){reward=1;}
			else {reward=0;}
			
			// establish new state of robot
			if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){newState=S1;}
			if(robot.getSensor1() > robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){newState=S2;}
			if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() > robot.getSensor3()){newState=S3;}
			if(robot.getSensor1() < robot.getSensor2() && robot.getSensor2() < robot.getSensor3()){newState=S4;}
			
			// update table
			updateTable();
			
			// check if stop threshold is met
			if(robot.getSensor2()>STOP_THRESHOLD)
			{
				// stop
				robot.beep();
				robot.beep();
				robot.beep();
				break;
			}
		}
	}
}
