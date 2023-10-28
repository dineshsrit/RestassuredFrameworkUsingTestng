package com.spotify.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	
	
	public static String generateName()
	{
		Faker faker= new Faker();
		return	faker.regexify("[a-zA-Z0-9,]{15}");
	}
	
	public static String generateDescription()
	{
		Faker faker= new Faker();
		return	faker.regexify("[a-zA-Z0-9,-#@]{15}");
	}
	

}
