package com.example.crazynet.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class reviewItem implements Parcelable{

	@SerializedName("author")
	private String author;

	@SerializedName("id")
	private String id;

	@SerializedName("content")
	private String content;

	@SerializedName("url")
	private String url;

	protected reviewItem(Parcel in) {
		author = in.readString();
		id = in.readString();
		content = in.readString();
		url = in.readString();
	}

	public static final Creator<reviewItem> CREATOR = new Creator<reviewItem>() {
		@Override
		public reviewItem createFromParcel(Parcel in) {
			return new reviewItem(in);
		}

		@Override
		public reviewItem[] newArray(int size) {
			return new reviewItem[size];
		}
	};

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"reviewItem{" +
			"author = '" + author + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(author);
		dest.writeString(id);
		dest.writeString(content);
		dest.writeString(url);
	}
}