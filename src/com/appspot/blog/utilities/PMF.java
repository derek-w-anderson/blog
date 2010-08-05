package com.appspot.blog.utilities;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Singleton wrapper for a PersistenceManagerFactory, which is used to get a
 * PersistenceManager that can interact with the datastore.
 * 
 * @author Derek W. Anderson
 */
public final class PMF
{
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() { }

	public static PersistenceManagerFactory getInstance() {
		return pmfInstance;
	}
}
