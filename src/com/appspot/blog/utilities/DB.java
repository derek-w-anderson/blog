package com.appspot.blog.utilities;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public final class DB
{
	public static Object get(Class<?> cls, String field, Object value, PersistenceManager pm) 
	{
		Query query = pm.newQuery(cls, field + " == \"" + value + "\"");
		query.setUnique(true);
		return query.execute();
	}
	
	public static List<?> getList(Class<?> cls, String field, String value, PersistenceManager pm) 
	{
		Query query = pm.newQuery(cls, field + " == \"" + value + "\"");
		return (List<?>) query.execute();
	}
	
	public static List<?> getList(Class<?> cls, String ordering, int start, int stop, PersistenceManager pm) 
	{
		Query query = pm.newQuery(cls);
		query.setOrdering(ordering);
		query.setRange(start, stop);
		return (List<?>) query.execute();
	}
}
