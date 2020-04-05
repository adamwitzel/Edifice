
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameUtils {

	//Split common lead/rear
	//Remove bracketed lead/rear
	//Remove bracketed all
	//		Find resolution
	//Find year
	//Find series name*
	//Find episode name*
	//Split from lead/rear char
	//Get file extensions
	//
	
	public static String[] findResolutions(String[] fileNames)
	{
		String[] resolutions = {"2160p", "1080p", "720p", "480p"};
		ArrayList<String> fileResolutions = new ArrayList<String>();
	
		for(String fileName : fileNames)
		{
			boolean resFlag = false;
			for(String resolution : resolutions)
			{
				if(fileName.indexOf(resolution) != -1)
				{
					fileResolutions.add(resolution);
					resFlag = true;
				}
			}
			if(!resFlag) 
			{
				fileResolutions.add("Other");
			}
		}
		
		return fileResolutions.toArray(new String[fileResolutions.size()]);
	}
	
	public static String[] getFileExtensions(String[] fileNames)
	{
		String[] extensions = new String[fileNames.length];
		
		for(int i = 0; i < fileNames.length; i++)
		{
			extensions[i] = getFileExtension(fileNames[i]);
		}
		
		return extensions;
	}
	
	public static String getFileExtension(String fileName) {
		
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        {
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        else 
        {
        	return "";
        }
    }
	
	public static String splitCommonLead(String[] fileNames)
	{
		String commonLead = "";
		
		
		for(int i = 0; i < fileNames[0].length(); i++)
		{
			for(String fileName : Arrays.copyOfRange(fileNames, 1, fileNames.length))
			{
				if(fileName.charAt(i) != fileNames[0].charAt(i))
				{
					return commonLead;
				}
			}
			
			commonLead += fileNames[0].charAt(i);
		}
		
		return commonLead;
	}
	
	public static String[] findAllMatches(String inputString, String pattern)
	{
		ArrayList<String> matches = new ArrayList<String>();
		
		Matcher m = Pattern.compile(pattern).matcher(inputString);
		while (m.find()) {
			matches.add(m.group());
		}
		
		return matches.toArray(new String[matches.size()]);
	}
	
	private static String[] removeAllBracketed(String[] fileNames)
	{
		return null;
	}
	
	
	
}


















