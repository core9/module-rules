package io.core9.rules.conditions;

import io.core9.plugin.server.request.Request;

import java.util.Map;

public class AndCondition implements Condition {
	
	private Condition a;
	private Condition b;

	@Override
	public Condition setRequest(Request request) {
		return this;
	}

	@Override
	public Condition setContext(Map<String, Object> context) {
		a = (Condition) context.get("a");
		b = (Condition) context.get("b");
		return null;
	}
	
	@Override
	public boolean isMet() {
		return a.isMet() && b.isMet();
	}

}
