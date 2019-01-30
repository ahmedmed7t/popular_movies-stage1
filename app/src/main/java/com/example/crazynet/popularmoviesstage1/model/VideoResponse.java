package com.example.crazynet.popularmoviesstage1.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VideoResponse{

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private ArrayList<videoItem> results;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResults(ArrayList<videoItem> results){
		this.results = results;
	}

	public ArrayList<videoItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"VideoResponse{" + 
			"id = '" + id + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}