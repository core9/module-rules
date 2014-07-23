package io.core9.rules.handlers;

import io.core9.plugin.server.request.Request;
import io.core9.rules.Rule;
import io.core9.rules.Status;
import io.core9.rules.Status.Type;

public class DenyHandler extends ClientHandler {
	
	@Override
	public Status handle(Rule rule, Request request, Status status) {
		Status clientStatus = super.handle(rule, request, status);
		if(clientStatus.getType() == Type.PROCESS) {
			if(PathHandler.matches(rule, request.getPath())) {
				return clientStatus.setType(Type.DENY);
			}
		} else if (clientStatus.getType() == Type.DENY) {
			if(PathHandler.matches(rule, request.getPath())) {
				return clientStatus.setType(Type.PROCESS);
			}
		}
		return status;
	}

}
