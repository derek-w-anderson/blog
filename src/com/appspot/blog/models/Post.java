package com.appspot.blog.models;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.appspot.blog.exceptions.EmptyFieldException;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;

/**
 * Model implementation for blog posts. 
 * 
 * @author Derek W. Anderson
 */
@PersistenceCapable
public class Post implements Serializable
{
	private static final long serialVersionUID = -5799880202844263618L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String title;
	
	@Persistent(defaultFetchGroup="true")
	private Text content;
	
	@Persistent
	private User author;
	
	@Persistent
	@Embedded
	private Blog blog;
	
	@Persistent
	private Date dateCreated;

	public Post(String title, Text content, User author, Blog blog) throws EmptyFieldException {
		setTitle(title);
		setContent(content);
		setAuthor(author);
		setBlog(blog);
		setDateCreated(new Date());
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) throws EmptyFieldException {
		// Verify that the field isn't empty
		if (title.equals(""))
			throw new EmptyFieldException("title");
		
		this.title = title;
	}
	
	public Text getContent() {
		return content;
	}

	public void setContent(Text content) throws EmptyFieldException {
		// Verify that the field isn't empty
		if (title.equals(""))
			throw new EmptyFieldException("title");
		
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
