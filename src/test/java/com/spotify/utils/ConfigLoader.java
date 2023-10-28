package com.spotify.utils;

import java.util.Properties;

public class ConfigLoader {
	
	
	private static  Properties prop;
	private static ConfigLoader configloader;
	
	private  ConfigLoader()
	{
		prop=	PropertyUtils.propertyLoader("resources/config.properties");
		
	}
	
	public static ConfigLoader getInstance()
	{
		
		if(configloader==null)
		{
			configloader= new ConfigLoader();
		}
		return configloader;
		
	}

	public String getClientId()
	{
		String clientid= prop.getProperty("client_id");
		if(clientid!=null) return clientid;
		else throw new RuntimeException("Client_id not defined in the config.properties file");
	}
	
	public String getClientSecret()
	{
		String client_secret= prop.getProperty("client_secret");
		if(client_secret!=null) return client_secret;
		else throw new RuntimeException("client_secret not defined in the config.properties file");
	}
	
	public String getrefreshToken()
	{
		String refresh_token= prop.getProperty("refresh_token");
		if(refresh_token!=null) return refresh_token;
		else throw new RuntimeException("refresh_token not defined in the config.properties file");
	}
	
	
	public String getgrant_type()
	{
		String grant_type= prop.getProperty("grant_type");
		if(grant_type!=null) return grant_type;
		else throw new RuntimeException("grant_type not defined in the config.properties file");
	}
	
	public String getuser_id()
	{
		String user_id= prop.getProperty("user_id");
		if(user_id!=null) return user_id;
		else throw new RuntimeException("user_id not defined in the config.properties file");
	}
	
	
	
	
	
	
	
}
