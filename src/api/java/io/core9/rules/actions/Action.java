package io.core9.rules.actions;

import io.core9.plugin.server.request.Request;

import java.util.Map;

/**
 * Use this interface to register runnable actions
 * These can be anything, from sending a redirect to executing an import
 * 
 * @author mark.wienk@core9.io
 *
 */
public interface Action {
	
	/**
	 * Set the context on a rule
	 * @param context
	 * @return
	 */
	Action setContext(Map<String,Object> context);
	
	/**
	 * Set the request on a rule
	 * @return
	 */
	Action setRequest(Request request);
	
	/**
	 * Execute the rule
	 */
	void execute();
}
