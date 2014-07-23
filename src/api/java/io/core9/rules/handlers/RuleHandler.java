package io.core9.rules.handlers;

import io.core9.plugin.server.request.Request;
import io.core9.rules.Rule;
import io.core9.rules.Status;

public interface RuleHandler {
	
	Status handle(Rule rule, Request request, Status status);

}
