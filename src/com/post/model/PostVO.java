package com.post.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

public class PostVO implements Serializable{
	private Integer postId;
	private Integer authorId;
	private String title;
	private String article;
	private Timestamp created;
	private String passed;
	

	public String getPassed() {
		return passed;
	}
	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", authorId=" + authorId + ", title=" + title + ", article=" + article
				+ ", created=" + created + "]";
	}
	
}
