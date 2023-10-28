package com.spotify.utils;

import java.util.Properties;

public class DataLoader {
	
	
	private static  Properties prop;
	private static DataLoader dataloaderconfig;
	
	private  DataLoader()
	{
		prop=	PropertyUtils.propertyLoader("resources/DataLoader.properties");
		
	}
	
	public static DataLoader getInstance()
	{
		
		if(dataloaderconfig==null)
		{
			dataloaderconfig= new DataLoader();
		}
		return dataloaderconfig;
		
	}

	public String getplaylistid()
	{
		String playlistid= prop.getProperty("playlistid");
		if(playlistid!=null) return playlistid;
		else throw new RuntimeException("playlistid not defined in the dataloader.properties file");
	}
	
	
	
	
	
	
	
}
