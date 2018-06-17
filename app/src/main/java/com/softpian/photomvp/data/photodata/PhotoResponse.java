package com.softpian.photomvp.data.photodata;

import com.squareup.moshi.Json;

public class PhotoResponse {

	private String stat;

	private Photos photos;

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

	public void setPhotos(Photos photos){
		this.photos = photos;
	}

	public Photos getPhotos(){
		return photos;
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
			"PhotoResponse{" +
			"stat = '" + stat + '\'' + 
			",photos = '" + photos + '\'' + 
			",httpStatuscode = '" + httpStatusCode + '\'' +
			",errorMessage = '" + errorMessage + '\'' +
			"}";
		}
}