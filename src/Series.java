import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Series {
	
	private String seriesName;
	
	private final File directory;
	
	private ArrayList<Season> seasons = new ArrayList<Season>();
	
	
	public Series(String directory)
	{
		this.directory = new File(directory);
		
		seriesName = this.directory.getName();
		
		String[] subDirs = FileOpUtils.getSubdirectories(directory);
		for(String subDir : subDirs)
		{
			seasons.add(new Season(directory + "\\" + subDir));
		}
		
		abbreviateSeasons();
		
		for(Season season : seasons)
		{
			season.setSeasonName(seriesName + " " + season.getSeasonName());
		}
		
	}
	
	public void renameFiles()
	{
		for(Season season : seasons)
		{
			season.renameFiles();
		}
	}
	
	public void setResolutions(String resolution)
	{
		for(Season season : seasons)
		{
			season.setResolutions(resolution);
		}
	}
	
	public String toString()
	{
		String outputString = "";
		
		outputString += "Series Directory: " + directory + "\n";
		outputString += "Series Name: " + seriesName + "\n";
		
		for(Season seasonIter : seasons)
		{
			outputString += seasonIter.toString(); 
		}
		
		return outputString;
	}
	
	public void abbreviateSeasons()
	{
		String sPatternString = "[Ss]((eries)|(eason))\\s?(\\d+)$";
		
		Pattern sPattern = Pattern.compile(sPatternString);
		
		for(Season season : seasons)
		{			
			if(season.getSeasonName().matches(sPatternString))
			{
				Matcher sMatcher = sPattern.matcher(season.getSeasonName());
				while (sMatcher.find()) {
					season.setSeasonName("S" + sMatcher.group(4));
				}
			}
			
		}
	}
}
