package net.doudouer.util;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import net.doudouer.domain.Movie;


public class MovieImporterUtil {

	public final static String TABLE_NAME = "movies";
	public final static String ID = "id";
	public final static String NAME = "movieName";
	public final static String TYPE = "type";
	public final static String DIRECTOR = "director";
	public final static String ACTOR = "actor";
	public final static String DESCRIPTION = "description";
	public final static String COUNTRY = "country";
	public final static String LANGUAGE = "language";
	public final static String RELEASEYEAR = "releaseYear";
	public final static String AVATARLINK = "avatarLink";
	
	
	public static void main(String[] args) {
		dataConverter();
	}
	
	public static List<Movie> dataConverter(){
		try {
			LineNumberReader lineReader = new LineNumberReader(new FileReader(
					"movies.dat"));
			String line = "";
			List<Movie> movieList = new ArrayList<Movie>();
			while ((line = lineReader.readLine()) != null) {
				line = line;
				line = new String(line.getBytes(),"utf-8");
				movieList.add(fillMovie(line));
			}
			return movieList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Movie fillMovie(String line) {
		
		System.out.println(line);
		Movie movie = new Movie();
		String[] mo = line.split("::");
		
		movie.setId(Long.parseLong(mo[0]));
		movie.setMovieName(convertData(mo[1]));
		movie.setType(convertData(mo[2]));
		movie.setDirector(convertData(mo[3]));
		movie.setActor(convertData(mo[4]));
		movie.setDescription(convertData(mo[5]));
		movie.setCountry(convertData(mo[6]));
		movie.setLanguage(convertData(mo[7]));
		movie.setReleaseYear(mo[8]);
		movie.setAvatarLink(mo[9]);
		
		return movie;
	}
	
	private static String convertData(String line){
		String[] t = line.split("\\|");
		if(!(t.length > 1)){
			return line;
		}
		
		List<String> dataList = new ArrayList<String>();
		for (int i = 0; i < t.length; i++) {
			dataList.add(t[i]);
		}
		
		String retVal = StringUtil.connectString(dataList, ",");
		return retVal;
	}
}
