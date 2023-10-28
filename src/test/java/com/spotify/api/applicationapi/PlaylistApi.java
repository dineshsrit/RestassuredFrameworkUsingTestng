package com.spotify.api.applicationapi;

import com.spotify.pojo.Item;
import com.spotify.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {
	
	//static String accesstoken="BQDcQ_dEkVJJ34--t8mHDbDvyyRLHDoCUwOGUW6l_tIgnJckvnoxPy3Z992Cl4mXVvB__BGHuctRyrMyngX0jeU_cQ-hzk4OPz3qyGWpwJGmC5TQzwQI-ebbzG0dZD_xPYg1dj6gcvufioV0F7V8wCS4-OMmoQSFh4gywyGbYdaGMkfzn3fByyGyPFTjtm3Ir8-UoChqweNgogZn_JLarjdfuldpo9r-Yx6BqxfY1AK2rnArFRNniJKse1AhDtBHsEyM_Z9XsdOMIhHH";
	
	@Step
	public static Response post(Item requestitem)
	{
		
		return RestSource.post(Route.USER+ "/" +ConfigLoader.getInstance().getuser_id() + "/"+ Route.PLAYLISTS, TokenManager.getToken(), requestitem);
		
	}
	
	
	
	public static Response post(Item requestitem, String token)
	{
		
		return RestSource.post(Route.USER+ "/" +ConfigLoader.getInstance().getuser_id() + "/"+ Route.PLAYLISTS, token, requestitem);
		
	}
	
	public static Response get(String itemid)
	{
		
		return RestSource.get(Route.PLAYLISTS+ "/" +itemid, TokenManager.getToken());
		
	}
}
