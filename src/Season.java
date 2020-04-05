import java.util.Arrays;
import java.util.stream.IntStream; 
import java.io.File;
import java.lang.Math; 

/* 		Assumptions:
 * Files are in order
 * Files start at one
 * Files are not split by season
 * Files are in folder named for the series
 * Files are only integer numbered, no 13.5 recap episodes
 * 
 */

public class Season {
	
	private String seasonName;
	
	private String[] resolutions;
	private String[] episodeNames;
	private int[] episodeNumbers;
	private String[] fileExtensions;
	
	private String[] fileNames;
	
	private final File directory;
	
	
	public Season(String directory)
	{
		this.directory = new File(directory);
		
		seasonName = this.directory.getName();
		
		fileNames = FileOpUtils.getFilesInDirectory(directory);
		
		episodeNames = new String[fileNames.length];
		for(int i = 0; i < fileNames.length; i++)
		{
			episodeNames[i] = "";
		}
		
		setFileExtensions(FileNameUtils.getFileExtensions(fileNames));
		setResolutions(FileNameUtils.findResolutions(fileNames));
		setEpisodeNumbers(1);
	}
	

	public String toString()
	{
		//int maxDigitsInNum = (int) Math.ceil(Math.log10(new Double(fileNames.length)));
		//String numFormatString = "%0" + maxDigitsInNum + "d";
		
		String outputString = "";
		outputString += ("Directory: " + directory + "\n");
		outputString += ("Season Name: " + seasonName + "\n");
		
		String[] newNames = generateNewNames();
		
		for(int i = 0; i < resolutions.length; i++)
		{
			outputString += newNames[i] + "\n";
		}
		
		return outputString;
	}
	
	public String[] generateNewNames()
	{
		int maxDigitsInNum = (int) Math.ceil(Math.log10(new Double(fileNames.length)));
		
		String[] newNames = new String[fileNames.length];
		String nameFormat = "%s - %0" + maxDigitsInNum + "d%s [%s].%s";
		
		for(int i = 0; i < fileNames.length; i++)
		{			
			
			newNames[i] = String.format(nameFormat, 
					seasonName, episodeNumbers[i], episodeNames[i], resolutions[i], fileExtensions[i]);
		}
		
		return newNames;
	}
	
	public void renameFiles()
	{
		String[] newNames = generateNewNames();
		
		for(int i = 0; i < newNames.length; i++)
		{
			FileOpUtils.renameFileName(directory + "\\" + fileNames[i], newNames[i]);
		}
		
		//fileNames = FileOpUtils.getFilesInDirectory(directory);
	}
	
	public void renameFiles(String[] newNames)
	{
		for(int i = 0; i < newNames.length; i++)
		{
			FileOpUtils.renameFileName(directory + "\\" + fileNames[i], newNames[i]);
		}
	}
	
	public String[][] extractEpisodeNums()
	{
		String[][] epNums = new String[fileNames.length][];
		String numPattern = "[\\.Ee\\s_-]([\\d\\.]+)[\\.\\s_-]";
		
		for(int i = 0; i < fileNames.length; i++)
		{
			epNums[i] = FileNameUtils.findAllMatches(fileNames[i], numPattern);
		}
		
		for(String[] epNum : epNums)
		{
			for(int i = 0; i < epNum.length; i++)
			{
				//System.out.println(epNum[i]);
				epNum[i] = epNum[i].substring(1, epNum[i].length() - 1);
			}
		}
		
		for(String[] epNum : epNums)
		{
			System.out.print("\n");
			for(int i = 0; i < epNum.length; i++)
			{
				System.out.print(epNum[i] + " ");
			}
		}
		
		return epNums;
	}
	
	public String[] chooseEpisodeNums(String[][] allEpNums) 
	{
		String[] epNums = new String[allEpNums.length];
		
		//avoid duplicates
		//Increasing
		//Mostly integers, otherwise .5s
		//all single poss, just go
		
		
		
		return epNums;
		
	}
	
	public int[] getEpisodeNumbers() {
		return episodeNumbers;
	}

	public void setEpisodeNumbers(int[] episodeNumbers) {
		this.episodeNumbers = episodeNumbers;
	}
	
	public void setEpisodeNumbers(int startNum)
	{
		int[] epNums = IntStream.range(startNum, startNum + resolutions.length).toArray(); 
		
		this.episodeNumbers = epNums;
	}

	public File getDirectory() {
		return directory;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String[] getResolutions() {
		return resolutions;
	}

	public void setResolutions(String[] resolutions) {
		this.resolutions = resolutions;
	}
	
	public void setResolutions(String resolution)
	{
		String[] resolutions = new String[fileNames.length];
		Arrays.fill(resolutions, resolution);
		setResolutions(resolutions);
	}

	public String[] getEpisodeNames() {
		return episodeNames;
	}

	public void setEpisodeNames(String[] episodeNames) {
		this.episodeNames = episodeNames;
	}
	
	public String[] getFileExtensions() {
		return fileExtensions;
	}

	public void setFileExtensions(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
	}


	public String[] getFileNames() {
		return fileNames;
	}


	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}


}










