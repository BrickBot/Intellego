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
* Provides a simple textual code editor
*
* @author Graham Ritchie
*/
public class CodeEditor extends JInternalFrame 
{
    static final int xOffset = 30, yOffset = 30;
	static int openFrameCount = 0;
	
	// the current file
	private File currentFile;
	
	//the current file's local name
	private String currentFileName;
	
	// the current file's directory
	private File currentDir;
	
	// the main code display pane
	private JEditorPane codePane;

    public CodeEditor() 
	{
        super("Code Editor: (no file)",true,true,true,true);
		++openFrameCount;
		
        // create the code editor GUI and put it in the window...
		
		// add the menu bar
		setJMenuBar(createMenuBar());
		
		// create and add the editor pane
		codePane=new JEditorPane();
		codePane.setVisible(true);
		codePane.setEditable(true);
		
		// put it in a scroll pane
		JScrollPane codeScrollPane = new JScrollPane(codePane);
        codeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        codeScrollPane.setPreferredSize(new Dimension(400,600));
        codeScrollPane.setMinimumSize(new Dimension(10, 10));
		
		// and add this to a main content pane
		JPanel contentPane = new JPanel();
		BoxLayout box = new BoxLayout(contentPane, BoxLayout.X_AXIS);
		contentPane.setLayout(box);
		contentPane.add(codeScrollPane);
		setContentPane(contentPane);
		
        // set the window size
        setSize(400,600);

        // and set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
	
	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// create the file menu
        JMenu fileMenu = new JMenu("File");
		
		// create the file menu items
		JMenuItem newFile = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
		JMenuItem save=new JMenuItem("Save");
		JMenuItem saveAs=new JMenuItem("Save As");
		JMenuItem close = new JMenuItem("Close");
		
		// create the action listeners
		newFile.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                createNewFile();
            }
        });
		
        open.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                openFile();
            }
        });
		
		save.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                saveFile();
            }
        });
		
		saveAs.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                saveFileAs();
            }
        });
		
		close.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                closeFile();
            }
        });
		
		// add the menu items to the menu
		fileMenu.add(newFile);
        fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(close);
		
		// create the lejos menu
        JMenu lejosMenu = new JMenu("leJOS");
		
		// create the lejos menu items
        JMenuItem compile = new JMenuItem("Compile");
		JMenuItem download = new JMenuItem("Link & Download");
		
		// create the action listeners
		
        compile.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                lejosCompileFile();
            }
        });
		
		download.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                downloadFile();
            }
        });
		
		// add the menu items to the menu
        lejosMenu.add(compile);
		lejosMenu.add(download);
		
		// create the sim menu
        JMenu simMenu = new JMenu("Simulator");
		
		// create the lejos menu items
		JMenuItem javaCompile = new JMenuItem("Compile");
        JMenuItem openInSim = new JMenuItem("Open controller in simulator");
		
		// create the action listeners
		
        openInSim.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                openFileInSim();
            }
        });
		
		javaCompile.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
			{
                javaCompileFile();
            }
        });
		
		// add the menu items to the menu
		simMenu.add(javaCompile);
        simMenu.add(openInSim);
		
		// add the menus to the menu bar
        menuBar.add(fileMenu);
		menuBar.add(lejosMenu);
		menuBar.add(simMenu);
		
		// return the final menu bar
        return menuBar;
	}
	
	/**
	* Creates a new editor pane
	*/
	public void createNewFile()
	{
		MainInterface.createCodeEditorFrame();
	}
	
	/**
	* Opens a file
	*/
	private void openFile()
	{
		JFileChooser chooser=new JFileChooser(new File(System.getProperties().getProperty("user.dir"),"controllers"));
		
		// add a filename filter for java files
		String[] extensions={".java"};
		chooser.addChoosableFileFilter(new FileChooserFilter(extensions,"Java Controller Files"));
		
		chooser.showOpenDialog(this);
		
		File file=chooser.getSelectedFile();
		
		try // to open the file
		{
			URL filePath=chooser.getSelectedFile().toURL();
			
			// set this file as the page in the codePane
			codePane.setPage(filePath);
			
			// set file as current file
			currentFile=file;
			
			// set current directory
			currentDir=chooser.getCurrentDirectory();
			
			// and change the title
			super.setTitle("Code Editor:  "+file.toString());
		}
		catch (Exception e)
		{
			MainInterface.displayMessage("Cannot open file");
			Intellego.addToLog("CodeEditor.openFile(): Failed to open file: "+e);
		}
	}
	
	/**
	* Saves the current file
	*/
	private void saveFile()
	{
		if (currentFile!=null)
		{
			// save file
			try
			{
				FileWriter fw=new FileWriter(currentFile);
				
				fw.write(codePane.getText());
				
				fw.close();
			}
			catch (Exception e)
			{
				MainInterface.displayMessage("Cannot save file");
				Intellego.addToLog("CodeEditor.saveFile(): Save to file failed: "+e);
			}
		}
		else
		{
			// no current file, so it must be new, so save as
			saveFileAs();
		}
	}
	
	private void saveFileAs()
	{
		JFileChooser chooser=new JFileChooser();
    	
		int returnValue=chooser.showSaveDialog(this);
		
		if (returnValue==JFileChooser.APPROVE_OPTION && currentFile!=null)
		{
			// save file
			try
			{
				FileWriter fw=new FileWriter(currentFile);
				
				fw.write(codePane.getText());
				
				fw.close();
			}
			catch (Exception e)
			{
				MainInterface.displayMessage("Cannot save file");
				Intellego.addToLog("CodeEditor.saveFile(): Save to file failed: "+e);
			}
		}
		else if(returnValue==JFileChooser.APPROVE_OPTION && currentFile==null)
		{
			// save file
			try
			{
				FileWriter fw=new FileWriter("filename.java");
				
				fw.write(codePane.getText());
				
				fw.close();
			}
			catch (Exception e)
			{
				MainInterface.displayMessage("Cannot save file");
				Intellego.addToLog("CodeEditor.saveFile(): Save to file failed: "+e);
			}
		}
		else
		{	
			// cancel, do nothing atm
		}
	}
	
	/**
	* Closes the current file
	*/
	private void closeFile()
	{
		// save the file
		saveFile();
		
		// and get rid of it
		currentFile=null;
		codePane.setText(" ");
		
		// and change the title
		super.setTitle("Code Editor:  (no file)");
	}
	
	/**
	* Compiles the current file with lejosc
	*/
	private void lejosCompileFile()
	{
		if(currentFile!=null)
		{
			externalCommand("lejosc "+currentFile.toString());
		}
		else
		{
			MainInterface.displayMessage("Cannot compile an empty file");
		}
	}
	
	/**
	* Compiles the current file with javac
	*/
	private void javaCompileFile()
	{
		if(currentFile!=null)
		{
			externalCommand("javac "+currentFile.toString());
		}
		else
		{
			MainInterface.displayMessage("Cannot compile an empty file");
		}
	}
	
	/**
	* Links and downloads the current file
	*/
	private void downloadFile()
	{
		if(currentFile!=null)
		{
			ControllerDL c=new ControllerDL(currentFile,currentDir);
		}
		else
		{
			MainInterface.displayMessage("You need to open a controller first");
		}
	}
	
	public void openFileInSim()
	{
		if(currentFile!=null)
		{
			// get rid of .java extension (to get the class name)
			String className=currentFile.getName();
			className=className.substring(0,className.length() - 5);
			
			// fire up a new simulator with this class
			MainInterface.createSimulatorFrame(className);
		}
		else
		{
			MainInterface.displayMessage("You need to open a controller first");
		}
	}

	/**
	* Processes all external calls, i.e calls to lejos & lejosc
	*/
	private void externalCommand(String cmd)
	{
		int len;
        byte buffer[] = new byte[1000];
		
		Intellego.addToLog("CodeEditor.externalCommand(): Processing External Command: "+cmd);
		
		try
		{
			Process external=Runtime.getRuntime().exec(cmd);
            InputStream ees = external.getErrorStream();
			
            try 
			{
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
             	Intellego.addToLog("CodeEditor.externalCommand(): error: "+e.getMessage());
             }
		}
		catch (Exception e) 
		{
        	Intellego.addToLog("CodeEditor.externalCommand(): error: "+e.getMessage());
       	}
	}
}
