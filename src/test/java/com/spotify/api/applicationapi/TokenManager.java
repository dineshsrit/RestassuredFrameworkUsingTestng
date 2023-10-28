package com.spotify.api.applicationapi;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken()
	{
		try
		{
			if(access_token==null || Instant.now().isAfter(expiry_time))
			{
				System.out.println("Renewing token");
				Response resp=renewToken();
				access_token=resp.path("access_token");
				int expiryDurationInSeconds=resp.path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds-300);
			}
			
			else
			{
				System.out.println("Token is good to Use");
			}
		}	
			catch (Exception e) {
				throw new RuntimeException("Abort!! Failed to get token");
			}
			return access_token;
		
		
		
}
	
	
	
	public static Response renewToken(){
	
	HashMap<String, String> formparams= new HashMap<String, String>();
	
	formparams.put("client_id", ConfigLoader.getInstance().getClientId());
	formparams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
	formparams.put("refresh_token", ConfigLoader.getInstance().getrefreshToken());
	formparams.put("grant_type", ConfigLoader.getInstance().getgrant_type());

	
	Response resp=	RestSource.postAccount(formparams);
	return resp;

/*
	if(resp.getStatusCode()!=200)
	{
		throw new RuntimeException("ABORT ! refresh token not generated");
	}
	
	System.out.println(resp.path("access_token"));
	return	resp.path("access_token");*/
	 
	
	
	}

}
