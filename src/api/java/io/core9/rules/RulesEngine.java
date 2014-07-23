package io.core9.rules;

import io.core9.core.plugin.Core9Plugin;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.VirtualHostProcessor;
import io.core9.plugin.server.request.Request;

import java.util.List;

public interface RulesEngine extends VirtualHostProcessor, Core9Plugin {
	
	static final String KEY_RULESETS = "ruleSets";

	RulesEngine addRuleSet(VirtualHost vhost, RuleSet ruleSet);

	Status handle(String ruleSet, Request request);
	
	Status handle(List<String> ruleSets, Request request);
}
