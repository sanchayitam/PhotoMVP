package com.softpian.photomvp.data.photodata;

import com.squareup.moshi.Json;

public class PhotoInfo{

	private String stat;

	private Photo photo;

	@Json(name = "code")
	private int httpStatusCode;

	@Json(name = "message")
	private String errorMessage;

	public void setStat(String stat){
		this.stat = stat;
	}

	public String getStat(){
		return stat;
	}

	public void setPhoto(Photo photo){
		this.photo = photo;
	}

	public Photo getPhoto(){
		return photo;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
 	public String toString(){
		return 
			"PhotoInfo{" + 
			"stat = '" + stat + '\'' + 
			",photo = '" + photo + '\'' +
			",httpStatusCode = '" + httpStatusCode + '\'' +
			",errorMessage = '" + errorMessage + '\'' +
			"}";
		}
}