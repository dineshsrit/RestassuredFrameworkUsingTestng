package com.spotify.api.applicationapi;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.api.SpecBuilder;

import io.restassured.response.Response;

public class RestSource {

	
	public static Response post(String path, String token, Object requestitem)
	{

	return	given(SpecBuilder.getRequestSpec())
		 .body(requestitem)
		 .auth().oauth2(token)
		 .when()
		 .post(path)
		 .then()
		 .spec(SpecBuilder.getResponseSpec())
		 .extract()
		 .response();
		
	}
	
	public static Response get(String path, String token)
	{
		return given(SpecBuilder.getRequestSpec())
		.auth().oauth2(token)
		 .when()
		 .get(path)
		 .then()
		 .spec(SpecBuilder.getResponseSpec())
		 .extract()
		 .response();
	}
	
	
	public static Response postAccount(HashMap<String, String> formparams)
	{
	return 	given(SpecBuilder.getAccountRequestSpec())
			.formParams(formparams)
	      .when()
	      .post(Route.API+Route.TOKEN)
	      .then()
	      .spec(SpecBuilder.getResponseSpec())
	      .extract()
	      .response();
	}
}
