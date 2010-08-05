package com.appspot.blog.models;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.users.User;

/**
 * Model implementation for comments on blog posts. 
 * 
 * @author Derek W. Anderson
 */
@PersistenceCapable
public class Comment implements Serializable
{
	private static final long serialVersionUID = -4300130800218134677L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String content;

	@Persistent
	private User author;

	@Persistent
	@Embedded
	private Post post;
	
	@Persistent
	private Date dateCreated;

	public Comment(String content, User author, Post post) {
		setContent(content);
		setAuthor(author);
		setPost(post);
		setDateCreated(new Date());
	}

	public Long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
