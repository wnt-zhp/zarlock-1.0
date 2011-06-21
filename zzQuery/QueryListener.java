package zzQuery;

import java.awt.Component;


public interface QueryListener {
	/**
	 * 
	 * @param q can be <b> null </b>
	 */
	public abstract void addQuery(Query q);
	/**
	 * 
	 * @param q can be <b> null </b>
	 */
	public abstract void removeQuery(Query q);
	/**
	 * 
	 * @param newOne can be <b> null </b>
	 * @param old can be <b> null </b>
	 */
	public abstract void switchQuery(Query newOne, Query old);
	public abstract void fireQuerrying();
	public abstract Component getDisplayedComponent();
}
