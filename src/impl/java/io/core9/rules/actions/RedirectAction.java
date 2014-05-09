package io.core9.rules.actions;

import java.util.Map;

import io.core9.plugin.server.request.Request;

public class RedirectAction implements Action {
	
	private Request request;
	private String toPath;

	@Override
	public Action setRequest(Request request) {
		this.request = request; 
		return this;
	}
	
	@Override
	public Action setContext(Map<String, Object> context) {
		this.toPath = (String) context.get("toPath");
		return this;
	}

	@Override
	public void execute() {
		this.toPath = parsePath(this.toPath);
		this.request.getResponse().sendRedirect(301, this.toPath);
	}
	
	private String parsePath(String path) {
		int paramStart = path.indexOf(':');
		if(paramStart == -1) {
			return path;
		} else {
			String end = path.substring(paramStart);
			int paramEnd = end.indexOf('/');
			String param = end;
			if(paramEnd != -1) {
				param = end.substring(0, paramEnd);
			}
			if(this.request.getParams().get(param) != null) {
				return parsePath(path.replace(param, (String) this.request.getParams().get(param)));
			} else {
				System.out.println("Param '" + param + "' not found on request, deleting value.");
				return parsePath(path.replace(param, ""));
			}
		}
	}

}
