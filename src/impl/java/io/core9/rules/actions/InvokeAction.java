package io.core9.rules.actions;

import io.core9.plugin.server.request.Request;

import java.util.Map;

public class InvokeAction implements Action {

	@Override
	public Action setContext(Map<String, Object> context) {
		return this;
	}

	@Override
	public Action setRequest(Request request) {
		return this;
	}

	@Override
	public void execute() {

	}

}
