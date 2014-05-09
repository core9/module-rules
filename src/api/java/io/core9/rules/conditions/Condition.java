package io.core9.rules.conditions;

import io.core9.plugin.server.request.Request;

import java.util.Map;

public interface Condition {
	
	Condition setRequest(Request request);
	
	Condition setContext(Map<String,Object> context);
	
	boolean isMet();

}
