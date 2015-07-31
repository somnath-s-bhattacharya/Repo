package com.View;

import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import javax.swing.JFileChooser;

public class FileChooser 
{
	
	
	public static String fileChose()
	{
	    JFileChooser fc= new JFileChooser();
	    int ret = fc.showOpenDialog(null);

	               if (ret== JFileChooser.APPROVE_OPTION) 
	               {
	             File file = fc.getSelectedFile();
	             String filename_win= file.getAbsolutePath();
	             String newsep = "//";
	             String oldsep ="\\";
	             String filename = filename_win.replace(oldsep, newsep);
	             System.out.println(filename);
	             return filename;
	            }

	           else
	             return null;
	 }
	public static String OutputDirChose()
	{	
		String OutputDirectory_win =  null;
		String OutputDirectory =  null;
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose Output Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
	    {
	    	OutputDirectory_win = chooser.getSelectedFile().getAbsolutePath();
	    } 
	    else 
	    {
	      System.out.println("No Selection ");
	    }
	    	String newsep = "//";
            String oldsep ="\\";
            OutputDirectory = OutputDirectory_win.replace(oldsep, newsep);
            OutputDirectory = OutputDirectory.concat("//");
     	return OutputDirectory;
	}
}
