package com.spotify.api;

public enum StatusCode {
	
	CODE_200(200, ""),
	CODE_201(201, ""),
	CODE_401(401,"Invalid access token"),
	CODE_400(400, "Missing required field: name");
	
	private final int code;
	private final String msg;
	
	StatusCode(int code, String msg)
	{
		this.code=code;
		this.msg=msg;
	}

	public String getmsg()
	{
		return msg;
	}
	
	public int getCode()
	{
		return code;
	}
	
}
