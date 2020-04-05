
public class facade {

	public static Season directoryToSeries(String directory)
	{
		Season series = new Season(directory);
		
		String[] files = FileOpUtils.getFilesInDirectory(directory);
		
		series.setResolutions(FileNameUtils.findResolutions(files));
		
		return series;
	}
	
	public static void main(String[] args) {
		
		String directory = FileOpUtils.selectDirectory("G:\\Video\\To sort");
		Season s1 = new Season(directory);
		s1.extractEpisodeNums();
		//s1.renameFiles(newNames);
		
		
		/*
		String directory = FileOpUtils.selectDirectory("G:\\Video\\To sort");
		Series series1 = new Series(directory);
		series1.setResolutions("720p");
		
		//series1.renameFiles();
		System.out.println(series1);
		*/

	}

}
