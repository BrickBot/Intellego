package util;

import java.io.*;

/**
 * This class provides a simple javax.swing.filechooser.FileFilter
 * implementation that accepts files based on their extension, or 
 * if they are a directory (to allow traversal of the filesystem).
 * multiple permissible extensions can be specified.
 *
 * @author Graeme Bell
 */
public class FileChooserFilter extends javax.swing.filechooser.FileFilter {

	/**
 	 * An array containing the extensions to match against - should 
	 * be strings in a String [] but some classes may produce strings in 
	 * an  Object[] arrays so accepts Object[] as well.
	 */
	Object[] extensions;

	/**
	 * This is a description of what files are being filtered by the 
	 * set of extensions given, for example ("all molecules"). 
	 */
	String description;

	/**
 	 * This method instantiates a new FileChooser filter.
	 * 
	 * @param extensions a String[] of extensions to map against
	 * @param description a String describing what these extensions
	 * 		      represent.
	 */
	public FileChooserFilter(String[] extensions,String description) {

		this.extensions=extensions;		
		this.description=description;

	} // end method

	/**
 	 * This method instantiates a new FileChooser filter.
	 * 
	 * @param extensions a Object[] of extensions to map against
	 * @param description a String describing what these extensions
	 * 		      represent.
	 */
	public FileChooserFilter(Object[] extensions, String description) {

		this.extensions=extensions;		
		this.description=description;

	} // end method

	/**
	 * This implements javax.swing.filechooser.FileFilter functionality.
	 * It accepts files based on their extension and also if they 
	 * are directories.
	 * 
	 * @param f the file to be checked for having a suitable extension
	 * 	    or for being a directory.
	 * @return true if file matches extension/is a directory. false otherwise.
	 */
        public boolean accept(File f) {
                
                // Strip path information

		for (int i=0 ; i<extensions.length ; i++) {

			if (f.getName().endsWith((String)extensions[i]) || f.isDirectory()) {

				return true;

			} // end if

		} // end for 
	
		// if we get here, we didn't match any extensions.

		return false; 

        } // end method


	/**
 	 * This is used to provide the JFileChooser with information
	 * on what the accept mapping represents.
	 * 	
	 * @return a String describing this FileFilters mapping.
 	 */
	public String getDescription() {

		// this could be extended to provide information on the molecule.
		return description;

	} // end method

} // end class

