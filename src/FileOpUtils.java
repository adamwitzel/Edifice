import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;

public class FileOpUtils {

	public static String[] getFilesInDirectory(String directory)
	{
		File folder = new File(directory);
		 
        String[] files = folder.list();
        
        return files;
	}
	
	public static String[] getSubdirectories(String directory)
	{
		File file = new File(directory);
		String[] directories = file.list(new FilenameFilter() 
		{
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		return directories;
	}
	
	public static boolean renameFileName(String oldName, String newName)
	{
		File oldFile = new File(oldName);
		String newNameString = oldFile.getParent() + "\\" + newName;
		File newFile = new File(newNameString);
		
		if(oldFile.renameTo(newFile)){
            return true;
        }else{
            return false;
        }


	}
	
	/*
	public static boolean renameFileFull(String oldName, String newName)
	{
		File oldFile = new File(oldName);
		File newFile = new File(newName);
		
		if(oldFile.renameTo(newFile)){
            return true;
        }else{
            return false;
        }

	}
	*/
	
	public static String selectFile()
	{
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("E:\\Downloads\\jdown"));
	    chooser.setDialogTitle("Select File");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	return chooser.getSelectedFile().toString();
	    } else {
	    	System.out.println("No Selection ");
	    	return "No Selection ";
	    }
	}

	
	public static String selectDirectory(String startLocation)
	{
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File(startLocation));
	    chooser.setDialogTitle("Select Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	return chooser.getSelectedFile().toString();
	    } else {
	    	System.out.println("No Selection ");
	    	return "No Selection ";
	    }
	}

}
