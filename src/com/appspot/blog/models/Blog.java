package com.appspot.blog.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.appspot.blog.exceptions.DuplicateFieldException;
import com.appspot.blog.exceptions.EmptyFieldException;
import com.appspot.blog.exceptions.InvalidFieldException;
import com.appspot.blog.exceptions.ModelException;
import com.appspot.blog.utilities.PMF;
import com.google.appengine.api.users.User;

/**
 * Model implementation for blogs. 
 * 
 * @author Derek W. Anderson
 */
@PersistenceCapable
public class Blog implements Serializable
{
	private static final long serialVersionUID = -2310861731769140912L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	@Persistent
	private String address;

	@Persistent
	private User author;

	@Persistent
	private Date dateCreated;

	public Blog(String name, String address, User author) throws ModelException {
		setName(name);
		setAddress(address);
		setAuthor(author);
		setDateCreated(new Date());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) throws ModelException {
		// Verify that the field isn't empty
		if (name.equals(""))
			throw new EmptyFieldException("name");
		
		this.name = name.replace('"', '\'');
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws ModelException {
		// Verify that the field isn't empty
		if (address.equals(""))
			throw new EmptyFieldException("address");

		// Verify that the address can be part of a valid URL
		if (address.length() < 6 || address.length() > 30 || !address.matches("[a-zA-Z0-9]+"))
			throw new InvalidFieldException("address", "It must consist of 6 to 30 alphanumeric characters (no spaces or punctuation).");

		// Verify that a blog with the same address does not already exist
		Blog.checkForDuplicateAddress(address);
		
		this.address = address;
	}
	
	@SuppressWarnings("unchecked")
	public static void checkForDuplicateAddress(String address) throws ModelException {
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		try {
			Query query = pm.newQuery(Blog.class, "address == '" + address + "'");
			List blogs = (List) query.execute();

			if (!blogs.isEmpty())
				throw new DuplicateFieldException("blog", "address");

		} finally {
			pm.close();
		}
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
