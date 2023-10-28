package com.spotify.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	
	
	
	
	public static Properties propertyLoader(String filepath)
	{
		
		Properties property=new Properties();
		BufferedReader reader;
		try
		{
			reader=new BufferedReader(new FileReader(filepath));
		
		try
		{
			property.load(reader);
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed to load the Property File");
		}
		
	}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException("File not found");
	
}

		return property;
		
	}

}
