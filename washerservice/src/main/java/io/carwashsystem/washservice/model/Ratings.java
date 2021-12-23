package io.carwashsystem.washservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

@Document(collection="Rating")
public class Ratings {
	
	
	@Id
	int id;
	String washerName;
	int rating;
	String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWasherName() {
		return washerName;
	}
	public void setWasherName(String washerName) {
		this.washerName = washerName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Ratings [id=" + id + ", washerName=" + washerName + ", rating=" + rating + ", comment=" + comment + "]";
	}
	
}
